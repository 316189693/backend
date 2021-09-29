package com.htjy.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RabbitMqConsumerTest {
    static String EXCHANGE_NAME = "exg_1";
    static String QUEUE_NAME = "que_1";
    static String ROUTE_KEY = "route_key_1";
     static Channel channel = null;
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = null;
        Connection conn = null;

        try {
            connectionFactory = new ConnectionFactory();


            conn = connectionFactory.newConnection(new Address[]{new Address("localhost", 5672)});
            channel = conn.createChannel();
            channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {

                @Override
                public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                    System.out.println("handleRecoverOk:"+ new String(bytes));
                    try { TimeUnit.SECONDS . sleep(1) ; }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }

                }
            }
            );

        } catch (Exception e) {
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
