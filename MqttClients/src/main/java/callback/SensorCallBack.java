package callback;
import client.TemperatureTransducer;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SensorCallBack implements MqttCallback{

    TemperatureTransducer tt;

    public SensorCallBack(TemperatureTransducer tt) {
        this.tt = tt;
    }

    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        cause.printStackTrace();
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        String msg = new String(message.getPayload());
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + message.getQos());
        System.out.println("接收消息内容 : " + msg);

        if(msg.startsWith(tt.clientId)){
            tt.sendTemp();
        }
    }
}
