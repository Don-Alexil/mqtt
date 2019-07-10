package com.eileo.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class BrokerClientFactory  {

    public static String broker = "tcp://localhost:1883";

    /**
     * Returns a client without a persistent session and no last will
     * @param clientId
     * @return
     * @throws MqttException
     */
    public static MqttClient getNewClient(String clientId) throws MqttException {

        return getNewClient(clientId, false);
    }


    public static MqttClient getNewClient(String clientId, boolean persistentSession) throws MqttException {
        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(!persistentSession);
        return getNewClient(clientId, conOpt);
    }

    public static MqttClient getNewClient(String clientId, MqttConnectOptions connectOptions) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
        System.out.println("Connecting to broker: " + broker);
        mqttClient.connect(connectOptions);
        return mqttClient;
    }

}
