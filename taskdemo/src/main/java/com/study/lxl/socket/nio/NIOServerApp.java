package com.study.lxl.socket.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName NIOServerApp
 * @Author @lvxile
 * @Date 2019/7/25 19:24
 * @Description TODO
 */
public class NIOServerApp {
    //多路复用器
    private static Selector selector;

    private static Boolean stop = false;

    private static void init() {
        try {
            selector = Selector.open();
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(8008), 1024);
            channel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port: "+8008);
        }catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static void main(String[] args) throws IOException {
        init();
        while (!stop) {
            Set<SelectionKey> set = selector.selectedKeys();
            Iterator<SelectionKey> it = set.iterator();
            SelectionKey key;
            //通过对就绪状态的Channel集合进行迭代，可以进行网络的异步读写操作
            while (it.hasNext()) {
                key = it.next();
                it.remove();
                try {
                    handler(key);
                } catch (IOException e) {
                    if (key != null) {
                        key.cancel();
                        if (key.channel() != null) {
                            key.channel().close();
                        }
                    }
                }
            }
        }

        /*
         * 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源。
         */
        if(selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*
     * readBytes>0  读到了字节，对字节进行编解码；
     * readBytes=0  没有读取到字节，属于正常场景，忽略；
     * readByte=-1 链路已经关闭，需要关闭SocketChannel，释放资源
     */
    private static void handler(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                //建立tcp连接
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();

                ///将新创建的SocketChannel设置为异步非阻塞，同时也可以对其TCP参数进行设置，例如TCP接收和发送缓冲区的大小等。
                sc.register(selector, SelectionKey.OP_READ);
                sc.configureBlocking(false);
            }
            if (key.isReadable()) { // 可读
                //读取通道的内容
                SocketChannel channel = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int bytecount = channel.read(buffer);
                if (bytecount > 0){
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("The time server receive order: "+body);
                    //如果请求指令是"QUERY TIME ORDER"则把服务器的当前时间编码后返回给客户端
                    String currentTime="QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(
                            System.currentTimeMillis()).toString() : "BAD ORDER";
                    doWrite(channel,currentTime);

                }else if (bytecount <0) {
                    key.cancel();
                    channel.close();
                }
            }
        }
    }

    private static void doWrite(SocketChannel channel,String response) throws IOException{
        if(response!=null&& response.trim().length()>0){
            byte[] bytes=response.getBytes();
            ByteBuffer writeBuffer=ByteBuffer.allocate(bytes.length);
            //调用ByteBuffer的put操作将字节数组复制到缓冲区
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);

            /*
             * 需要指出的是，由于SocketChannel是异步非阻塞的，它并不保证一次性能够把需要发送的字节数组发送完，
             * 此时会出现“写半包”问题，我们需要注册写操作，不断轮询Selector，将没有发送完毕的ByteBuffer发送完毕，
             * 可以通过ByteBuffer的hasRemaining()方法判断消息是否发送完成。
             * 此处仅仅是各简单的入门级例程，没有演示如何处理“写半包”场景，后面会说到。
             */
        }
    }


}
