package com.eileo.mqtt;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Random;

public class MqttPublishSample {

    public static void main(String[] args) {

        final Random random = new Random();
        final int id = random.nextInt();
        String content      = "Message from MqttPublishSample Alert Presistent " + id;
        String clientId     = "Zibox" + id;

        try {
            MqttClient sampleClient = BrokerClientFactory.getNewClient(clientId);
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(Topics.Example.getQos());
            message.setRetained(true);
            sampleClient.publish(Topics.Example.getName(), message);
            System.out.println("Message published");
            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
}
