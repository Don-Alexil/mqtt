package com.eileo.mqtt;

import org.eclipse.paho.client.mqttv3.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageSubscriber implements MqttCallback {

    MqttClient client;

    public MessageSubscriber() throws MqttException {

        this.client = BrokerClientFactory.getNewClient("JavaSubscriber", false);
        this.client.setCallback(this);
        this.client.subscribe(Topics.Example.getName(), Topics.Example.getQos());
    }

    public void connectionLost(Throwable cause) {
        System.out.println("Connection Lost ... ");
    }

    public void messageArrived(String topic, MqttMessage message) {

        final String messageContent = String.format("%20s : %s -> %s", topic,
                message.getId(), new String(message.getPayload()));
        System.out.println(messageContent);
    }

    public void deliveryComplete(IMqttDeliveryToken token) {

    }

    public static void main(String[] args) throws Exception{
        final MessageSubscriber messageSubscriber = new MessageSubscriber();

        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new SelfSubmitableTask(executorService));
    }

    static class SelfSubmitableTask implements Runnable {

        private final ExecutorService executorService;

        public SelfSubmitableTask(ExecutorService executorService) {
            this.executorService = executorService;
        }

        @Override
        public void run() {
            executorService.submit(new SelfSubmitableTask(executorService));

            try {
                System.out.println("A nap a day keeps the doctory away ... ");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Someone wake me up ...");
            }

        }
    }

}
