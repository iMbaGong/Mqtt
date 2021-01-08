package client;

import org.eclipse.paho.client.mqttv3.MqttException;


import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

public class LivingRoomTT extends TemperatureTransducer{

    public LivingRoomTT(){
        clientId = "LivingRoom";

        tempFunc = new float[]{0,0,0,0,
                0,0,0,0,
                0,0,0,0,
                -1,-1,-1,-1,
                -1,0,2,3,
                3,2,1,1,
                2,2,2,2,
                3,3,3,3,
                3,3,3,3,
                3,3,3,3,
                4,4,4,4,
                4,4,4,4,
                4,4,4,4,
                4,4,4,4,
                3,3,3,3,
                3,3,3,3,
                2,2,2,2,
                1,1,1,1,
                2,2,3,4,
                4,4,4,4,
                5,5,5,5,
                3,3,2,1,
                0,0,0,0,
                0,0,0,0};
    }

    public static void main(String[] args) throws MqttException {
        LivingRoomTT client = new LivingRoomTT();
        client.start();
    }
}
