###redis rdb文件
1 save: 在主线程中执行，会导致阻塞

2 bgsave: 创建一个子线程，专门用于写入RDB文件，避免了主线程的阻塞，redis rdb默认设置。(copy on write,写时复制)

3 使用建议：
    
    a)对于数据不能丢失的场景，尽量使用rdb快照与aop结合的方式做持久化，版本要求4.0+
    b)如果允许分钟级别的数据丢失，可以只是用rdb
    c)如果只用AOF，优先使用everysec的配置选项(appendfsync=always ,everysec,no)，性能，可用性平衡考虑

4 可用性架构：    
      
      主从
        读操作：主从库都可以接受
        写操作：首先到主库执行，然后主库将操作同步给从库    
        applicaof()设置主库从属关系,:applicaof 172.16.19.3 6379(5.0version之前使用slaveof)
        从库向主库发送psync命令，要求同步，参数包括主库的runnId(?)和复制进度offset(-1=第一次复制)。
      
      主-从-从模式  通过“主 - 从 - 从”模式将主库生成 RDB 和传输 RDB 的压力，以级联的方式分散到从库上。
        从库1 applicaof 主库
        从库2 applicaof 从库1  
        
      主从库连接中断处理
      当主从库断连后，主库会把断连期间收到的写操作命令，写入 replication buffer，同时也会把这些操作命令也写入 repl_backlog_buffer 这个缓冲区
      repl_backlog_buffer 是一个环形缓冲区，主库会记录自己写到的位置，从库则会记录自己已经读到的位置。  
      repl_backlog_size配置参数一般为缓存数据区2倍
      
      哨兵机制
        基于 pub/sub 机制的哨兵集群组成,通过redis提供的消息订阅与发布机制进行信息交换-sentinal:hello频道
        哨兵通过INFO命令向主库发送命令获取从库的ip和端口信息。
        要保证所有哨兵实例的配置是一致的，尤其是主观下线的判断值 down-after-milliseconds
        哨兵成为leader的条件：a：获得半数以上的票数，b：得到的票数要达到配置的quorum阈值。
        主从切换只能由Leader执行，而成为Leader有两个必要的条件，所以当哨兵集群中实例异常过多时，会导致主从库无法切换
        
      切片集群方案-cluster
      hash槽-16384
        moved 重定向-指令，通知客户端hash槽所属的ip和端口
        ask ASK 命令表示两层含义：第一，表明 Slot 数据还在迁移中；第二，ASK 命令把客户端所请求数据的最新实例地址返回给客户端，此时，客户端需要给实例 3 发送 ASKING 命令，然后再发送操作命令。
        asking  
        Redis Cluster不采用把key直接映射到实例的方式，而采用哈希槽的方式原因：
        
        1、整个集群存储key的数量是无法预估的，key的数量非常多时，直接记录每个key对应的实例映射关系，这个映射表会非常庞大，这个映射表无论是存储在服务端还是客户端都占用了非常大的内存空间。
        2、Redis Cluster采用无中心化的模式（无proxy，客户端与服务端直连），客户端在某个节点访问一个key，如果这个key不在这个节点上，这个节点需要有纠正客户端路由到正确节点的能力（MOVED响应），这就需要节点之间互相交换路由表，每个节点拥有整个集群完整的路由关系。如果存储的都是key与实例的对应关系，节点之间交换信息也会变得非常庞大，消耗过多的网络资源，而且就算交换完成，相当于每个节点都需要额外存储其他节点的路由表，内存占用过大造成资源浪费。
        3、当集群在扩容、缩容、数据均衡时，节点之间会发生数据迁移，迁移时需要修改每个key的映射关系，维护成本高。
        4、而在中间增加一层哈希槽，可以把数据和节点解耦，key通过Hash计算，只需要关心映射到了哪个哈希槽，然后再通过哈希槽和节点的映射表找到节点，相当于消耗了很少的CPU资源，不但让数据分布更均匀，还可以让这个映射表变得很小，利于客户端和服务端保存，节点之间交换信息时也变得轻量。
        5、当集群在扩容、缩容、数据均衡时，节点之间的操作例如数据迁移，都以哈希槽为基本单位进行操作，简化了节点扩容、缩容的难度，便于集群的维护和管理。
        另外，补充一下Redis集群相关的知识，以及我的理解：
        Redis使用集群方案就是为了解决单个节点数据量大、写入量大产生的性能瓶颈的问题。多个节点组成一个集群，可以提高集群的性能和可靠性，但随之而来的就是集群的管理问题，最核心问题有2个：请求路由、数据迁移（扩容/缩容/数据平衡）。
        1、请求路由：一般都是采用哈希槽的映射关系表找到指定节点，然后在这个节点上操作的方案。
        Redis Cluster在每个节点记录完整的映射关系(便于纠正客户端的错误路由请求)，同时也发给客户端让客户端缓存一份，便于客户端直接找到指定节点，客户端与服务端配合完成数据的路由，这需要业务在使用Redis Cluster时，必须升级为集群版的SDK才支持客户端和服务端的协议交互。
        其他Redis集群化方案例如Twemproxy、Codis都是中心化模式（增加Proxy层），客户端通过Proxy对整个集群进行操作，Proxy后面可以挂N多个Redis实例，Proxy层维护了路由的转发逻辑。操作Proxy就像是操作一个普通Redis一样，客户端也不需要更换SDK，而Redis Cluster是把这些路由逻辑做在了SDK中。当然，增加一层Proxy也会带来一定的性能损耗。
        2、数据迁移：当集群节点不足以支撑业务需求时，就需要扩容节点，扩容就意味着节点之间的数据需要做迁移，而迁移过程中是否会影响到业务，这也是判定一个集群方案是否成熟的标准。
        Twemproxy不支持在线扩容，它只解决了请求路由的问题，扩容时需要停机做数据重新分配。而Redis Cluster和Codis都做到了在线扩容（不影响业务或对业务的影响非常小），重点就是在数据迁移过程中，客户端对于正在迁移的key进行操作时，集群如何处理？还要保证响应正确的结果？
        Redis Cluster和Codis都需要服务端和客户端/Proxy层互相配合，迁移过程中，服务端针对正在迁移的key，需要让客户端或Proxy去新节点访问（重定向），这个过程就是为了保证业务在访问这些key时依旧不受影响，而且可以得到正确的结果。由于重定向的存在，所以这个期间的访问延迟会变大。等迁移完成之后，Redis Cluster每个节点会更新路由映射表，同时也会让客户端感知到，更新客户端缓存。Codis会在Proxy层更新路由表，客户端在整个过程中无感知。
        除了访问正确的节点之外，数据迁移过程中还需要解决异常情况（迁移超时、迁移失败）、性能问题（如何让数据迁移更快、bigkey如何处理），这个过程中的细节也很多。
        Redis Cluster的数据迁移是同步的，迁移一个key会同时阻塞源节点和目标节点，迁移过程中会有性能问题。而Codis提供了异步迁移数据的方案，迁移速度更快，对性能影响最小，当然，实现方案也比较复杂。