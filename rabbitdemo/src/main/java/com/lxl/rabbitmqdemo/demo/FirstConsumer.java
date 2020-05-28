package com.lxl.rabbitmqdemo.demo;

import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Lukas
 * @since 2020/5/27 11:07
 **/
@Component
@PropertySource("classpath:rabbit.properties")
@RabbitListener(queues = "${com.lxl.firstqueue}",containerFactory = "rabbitListenerContainer")
public class FirstConsumer {

    @RabbitHandler
    public void process(Message message) {
        System.out.println("receive message "+message.getBody().toString());
    }

    public void process(Message message, AMQImpl.Channel channel) {
        System.out.println("receive message "+message.getBody().toString());
    }
}
