package com.lxl.rabbitmqdemo.demo.producer;

import com.lxl.rabbitmqdemo.demo.Mechant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Lukas
 * @since 2020/5/27 11:24
 **/
@Service
public class RabbitSender {

    @Autowired
    private AmqpTemplate lxlTemplate;

    @Value("lxl_direct_exchange")
    private String directExchange = "lxl_direct_exchange";
    private String directRoutingKey = "lxl.first";

    /**
     * 发送方法
     */
    public void send() {
        Mechant m = new Mechant(1, "lxl", "wu");
        lxlTemplate.convertAndSend(directExchange,directRoutingKey,m);
    }
}
