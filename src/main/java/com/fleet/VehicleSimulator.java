package com.fleet;

import java.util.Random;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class VehicleSimulator {

    public static void main(String[] args) throws Exception {

        //To establish a connection with the MQTT Broker,
        String broker = "tcp://localhost:1883";
        String topic = "fleet/diva/1/telemetry";

        MqttClient client = new MqttClient(broker, MqttClient.generateClientId());
        client.connect();

        Random rand = new Random();
        int i = 1;
        while(i <= 50){
            int vehicleId = i;
            int speed = rand.nextInt(60) + 40;
            int engineTemp = rand.nextInt(40) + 180;
            int fuel = rand.nextInt(90) + 10;

            double baseLat = 42.6526;
            double baseLon = -73.7562;

            double lat = baseLat + (Math.random() * 0.01);
            double lon = baseLon + (Math.random() * 0.01);

            String payload = "{ \"vehicleId\": " + vehicleId + ", \"speed\": " + speed +
                    ", \"engine_temp\": " + engineTemp +
                    ", \"fuel\": " + fuel +", \"latitude\": " + lat + ", \"longitude\": " + lon + "}";

            MqttMessage message = new MqttMessage(payload.getBytes());

            client.publish(topic, message);

            System.out.println("Published: " + payload);

            Thread.sleep(1000);

            i++;
        }
    }
}