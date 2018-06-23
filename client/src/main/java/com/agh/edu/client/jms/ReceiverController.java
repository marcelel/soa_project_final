package com.agh.edu.client.jms;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import java.io.Serializable;
import java.util.StringJoiner;

@Named
@SessionScoped
public class ReceiverController implements Serializable {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "java:jboss/jms/topic/messageTopic")
    private Topic topic;

    @Setter
    @Getter
    private String message;

    @PostConstruct
    public void init() {
        createReceiver();
    }

    public void start() {
        message = "";
    }

    public void createReceiver() {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
            String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
            MessageConsumer messageConsumer = session.createConsumer(topic, String.format("(username = '%s')", username));
            messageConsumer.setMessageListener(new Receiver(this));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message) {
        this.message = message;
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/jms", new FacesMessage(message));
    }

}
