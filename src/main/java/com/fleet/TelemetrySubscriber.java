package com.fleet;

import org.eclipse.paho.client.mqttv3.MqttClient;

public class TelemetrySubscriber {

    public static void main(String[] args) throws Exception {

        String broker = "tcp://localhost:1883";
        String topic = "fleet/diva/+/telemetry";

        MqttClient client = new MqttClient(broker, MqttClient.generateClientId());

        client.connect();

        client.subscribe(topic, (topic1, message) -> {

            String payload = new String(message.getPayload());

            System.out.println("Received from " + topic1 + ": " + payload);

        });

        System.out.println("Listening for telemetry...");

    while (true) {
    Thread.sleep(1000);
        }
    }
}