package com.agh.edu.client.jms;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

import javax.faces.application.FacesMessage;

@PushEndpoint("/jms")
public class JmsNotifyResource {

    @OnMessage(encoders = { JSONEncoder.class })
    public FacesMessage onMessage(FacesMessage message) {
        return message;
    }
}
