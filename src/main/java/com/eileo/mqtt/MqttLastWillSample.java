package com.eileo.mqtt;


import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttLastWillSample {

    public static void main(String[] args) {

        String clientId     = "Zibox";

        try {
            MqttConnectOptions connectionOtions = new MqttConnectOptions();

            connectionOtions.setWill(Topics.Example.getName(), "Burn my body ... ".getBytes(),
                    Topics.Example.getQos(), true);
            BrokerClientFactory.getNewClient(clientId, connectionOtions);
            System.exit(1);
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
