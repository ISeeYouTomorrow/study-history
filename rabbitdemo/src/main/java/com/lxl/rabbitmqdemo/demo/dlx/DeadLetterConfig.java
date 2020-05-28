package com.lxl.rabbitmqdemo.demo.dlx;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lukas
 * @since 2020/5/28 10:58
 **/
@Configuration
public class DeadLetterConfig {
    /**
     * 声明一个正常的业务queue，但是配置该queue的死信处理参数
     * @return
     */
    @Bean("mailQueue")
    public Queue mailQueue() {
        Map<String, Object> args = new HashMap<>();
        //设置死信交换机参数
        args.put("x-dead-letter-exchange","my_dead_exchange");
        //设置死信路由key
        args.put("x-dead-letter-routing-key", "mail_queue_fail");
        return new Queue("mailQueue", true, false, false, args);
    }

    /**
     * 声明一个正常业务的exchange，与mailqueue绑定
     * @return
     */
    @Bean("mailExchange")
    public DirectExchange mailExchange() {
        return new DirectExchange("mailExchange", true, false);
    }

    /**
     * 声明业务queue与业务exchange的绑定
     * @param mailQueue
     * @param mailExchange
     * @return
     */
    @Bean
    public Binding mailBinding(@Qualifier("mailQueue")Queue mailQueue,
                               @Qualifier("mailExchange")DirectExchange mailExchange) {
        return BindingBuilder.bind(mailQueue).to(mailExchange).with("mail_routing_key");
    }

    /**
     * 声明死信队列，用于接收死信交换机转发的死信
     * @return
     */
    @Bean("my_dead_queue")
    public Queue deadQueue() {
        return new Queue("my_dead_queue", true, false, false);
    }
    @Bean("my_dead_exchange")
    public DirectExchange deadExchange() {
        return new DirectExchange("my_dead_exchange", true,false);
    }

    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("mail_queue_fail");
    }
}
