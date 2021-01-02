package client;

import org.eclipse.paho.client.mqttv3.MqttException;


import java.util.concurrent.ScheduledExecutorService;

public class LivingRoomTT extends TemperatureTransducer{

    public LivingRoomTT(){
        clientId = "LivingRoom";
    }

    public static void main(String[] args) throws MqttException {
        LivingRoomTT client = new LivingRoomTT();
        client.start();
    }
}
