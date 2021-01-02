package client;

import callback.SensorCallBack;
import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TemperatureTransducer {

    public static final String HOST = "tcp://127.0.0.1:1883";
    public static final String TEMPERATURE_TOPIC = "LoT/Temperature";
    public static final String CONTROL_TOPIC = "Control";
    public static final int KEEP_ALIVE_INTERVAL = 3600;
    public static final int SEND_TEMP_INTERVAL = 10;

    public String MY_TOPIC;
    public String clientId;
    public MqttClient client;
    public MqttConnectOptions options;
    public String userName = "admin";
    public String passWord = "admin";

    public ScheduledExecutorService scheduler;

    public void start() {
        try {
            // host为主机名，clientId即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(HOST, clientId, new MemoryPersistence());
            // MQTT的连接设置
            options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
            options.setUserName(userName);
            // 设置连接的密码
            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(KEEP_ALIVE_INTERVAL);
            // 设置回调
            client.setCallback(new SensorCallBack());

            MY_TOPIC = TEMPERATURE_TOPIC + "/" + clientId;
            MqttTopic tempTopic = client.getTopic(MY_TOPIC);

            MqttTopic ctrlTopic = client.getTopic(CONTROL_TOPIC);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
            options.setWill(tempTopic, (clientId + " disconnected").getBytes(), 2, true);

            client.connect(options);
            //订阅消息
            client.subscribe(CONTROL_TOPIC, 1);

            scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(new SendTemp(), 0, SEND_TEMP_INTERVAL, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class SendTemp implements Runnable {
        public void run() {
            try {
                sendTemp();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendTemp() throws MqttException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String now = formatter.format(new Date());
        Random random = new Random();
        JSONObject msg = new JSONObject();
        msg.put("date",now);
        msg.put("location",clientId);
        msg.put("temp",Float.parseFloat(String.format("%.2f",random.nextDouble() * 50 - 10)));
        MqttMessage message = new MqttMessage(msg.toJSONString().getBytes());
        client.publish(MY_TOPIC, message);
    }

}
