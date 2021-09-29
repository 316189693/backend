package com.htjy.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqPublishTest {
     static  String EXCHANGE_NAME = "exg_1";
     static String QUEUE_NAME = "que_1";
     static String  ROUTE_KEY= "route_key_1";
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection conn = null;
        Channel channel = null;
        try{
            connectionFactory.setHost("localhost");

            connectionFactory.setPort(5672);
            connectionFactory.setVirtualHost("/");

            conn = connectionFactory.newConnection();
            channel = conn.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME,"direct", true, false ,null);
            channel.queueDeclare(QUEUE_NAME,true,false,false,null);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTE_KEY);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTE_KEY+"1");
            byte[] messageBodyBytes = "Hello, fff world!".getBytes();
            channel.basicPublish(EXCHANGE_NAME, ROUTE_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBodyBytes);
            channel.basicPublish(EXCHANGE_NAME, ROUTE_KEY+"1", MessageProperties.PERSISTENT_TEXT_PLAIN, messageBodyBytes);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
                conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

        }

    }

}
