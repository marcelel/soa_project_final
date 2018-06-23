package com.agh.edu.server.service;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.*;

@ApplicationScoped
public class JMSService {

    private static final String TOPIC = "topic";

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:jboss/jms/topic/messageTopic")
    private Topic topic;

    public void sendMessage(String name, String value, String message) {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(topic);
            connection.start();
            TextMessage textMessage = session.createTextMessage(message);
            textMessage.setStringProperty(name, value);
            messageProducer.send(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
