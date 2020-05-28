package com.lxl.rabbitmqdemo.demo;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Lukas
 * @since 2020/5/27 10:35
 **/
@Configuration
@PropertySource("classpath:rabbitmq.properties")
public class Config {
    @Value("lxl_fanout_exchange")
    private String fanoutExchange;
    @Value("lxl_topic_exchange")
    private String topicExchange;
    @Value("lxl_direct_exchange")
    private String directExchange;

    @Value("lxl_first_queue")
    private String firstQueue;
    @Value("lxl_second_queue")
    private String secondQueue;
    @Value("lxl_third_queue")
    private String thirdQueue;

    /**
     * 创建交换机
     * @return
     */
    @Bean("lxlDirectExchange")
    public DirectExchange directExchange() {
        return new DirectExchange(directExchange);
    }

    @Bean("lxlFanoutExchange")
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchange);
    }

    @Bean("lxlTopicExchange")
    public TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }

    /**
     * 创建队列
     * @return
     */
    @Bean("firstQueue")
    public Queue getFirstQueue() {
        return new Queue(firstQueue);
    }

    @Bean("secondQueue")
    public Queue getSecondQueue() {
        return new Queue(secondQueue);
    }

    @Bean("thirdQueue")
    public Queue getThirdQueue() {
        return new Queue(thirdQueue);
    }

    /**
     * 定义绑定关系bingding
     * @return
     */
    @Bean
    public Binding bindFirst(@Qualifier("firstQueue")Queue firstQueue,
                             @Qualifier("lxlDirectExchange")DirectExchange directExchange) {
        return BindingBuilder.bind(firstQueue).to(directExchange).with("lxl.first");
    }

    /**
     * 订阅类型的绑定
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindSecond(@Qualifier("secondQueue")Queue queue,
                              @Qualifier("lxlTopicExchange")TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("*.lxl.*");
    }

    /**
     * 广播类型的交换机绑定
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindThird(@Qualifier("thirdQueue")Queue queue,
                              @Qualifier("lxlFanoutExchange")FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    /**
     * 定义消息发送格式json的模板
     * @param factory
     * @return
     */
    @Bean
    public RabbitTemplate lxlTemplate(final ConnectionFactory factory) {
        final RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

}
