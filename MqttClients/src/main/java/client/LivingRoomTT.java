package client;

import callback.SensorCallBack;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.ScheduledExecutorService;

public class LivingRoomTT extends TemperatureTransducer{

    public LivingRoomTT(String id){
        clientId = id;
    }

    public static void main(String[] args) throws MqttException {
        LivingRoomTT client = new LivingRoomTT("LivingRoom");
        client.start();
    }
}
