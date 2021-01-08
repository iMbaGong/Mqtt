package yulus.lot.mqtt.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.util.StringUtils;
import yulus.lot.mqtt.entity.*;
import yulus.lot.mqtt.service.*;


@Configuration
@IntegrationComponentScan
public class MqttConfig {

    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.url}")
    private String hostUrl;

    @Value("${spring.mqtt.client.id}")
    private String clientId;

    @Value("${spring.mqtt.default.topic}")
    private String defaultTopic;

    @Autowired
    LivingService livingService;
    @Autowired
    BedService bedService;
    @Autowired
    BathService bathService;
    @Autowired
    DiningService diningService;
    @Autowired
    BalconyService balconyService;

    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{hostUrl});
        return mqttConnectOptions;
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientId, mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(defaultTopic);
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Value("${spring.mqtt.consumer.defaultTopic}")
    private String consumerDefaultTopic;

    @Value("${spring.mqtt.consumer.clientId}")
    private String consumerClientId;


    /**
     * MQTT消息订阅绑定（消费者）
     *
     * @return {@link org.springframework.integration.core.MessageProducer}
     */

    @Bean
    public MessageProducer inbound() {
        // 可以同时消费（订阅）多个Topic
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        consumerClientId, mqttClientFactory(),
                        StringUtils.split(consumerDefaultTopic, ","));
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        // 设置订阅通道
        adapter.setOutputChannel(mqttInboundChannel());
        return adapter;
    }

    /**
     * MQTT信息通道（消费者）
     *
     * @return {@link org.springframework.messaging.MessageChannel}
     */
    @Bean(name = "mqttInboundChannel")
    public MessageChannel mqttInboundChannel() {
        return new DirectChannel();
    }

    /**
     * MQTT消息处理器（消费者）
     *
     * @return {@link org.springframework.messaging.MessageHandler}
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInboundChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("Get Message: " + message.getPayload());
                if(message.getPayload().toString().contains("disconnected")){
                    if(message.getPayload().toString().contains("BathRoom")){
                        TTState.bathTT=false;
                    }
                    else if(message.getPayload().toString().contains("DiningRoom")){
                        TTState.diningTT=false;
                    }
                    else if(message.getPayload().toString().contains("LivingRoom")){
                        TTState.livingTT=false;
                    }
                    else if(message.getPayload().toString().contains("BedRoom")){
                        TTState.bedTT=false;
                    }
                    else if(message.getPayload().toString().contains("Balcony")){
                        TTState.balconyTT=false;
                    }
                }
                else if(!message.getPayload().toString().contains("disconnected")){
                    JSONObject jsonObject = JSONObject.parseObject(message.getPayload().toString());
                    if(message.getPayload().toString().contains("BathRoom")){
                        bathService.update(JSON.toJavaObject(jsonObject, BathTemp.class));
                        TTState.bathTT=true;
                        TTState.bathTemp = jsonObject.getFloat("temp");
                    }
                    else if(message.getPayload().toString().contains("DiningRoom")){
                        diningService.update(JSON.toJavaObject(jsonObject, DiningTemp.class));
                        TTState.diningTT=true;
                        TTState.diningTemp = jsonObject.getFloat("temp");
                    }
                    else if(message.getPayload().toString().contains("LivingRoom")){
                        livingService.update(JSON.toJavaObject(jsonObject, LivingTemp.class));
                        TTState.livingTT=true;
                        TTState.livingTemp = jsonObject.getFloat("temp");
                    }
                    else if(message.getPayload().toString().contains("BedRoom")){
                        bedService.update(JSON.toJavaObject(jsonObject, BedTemp.class));
                        TTState.bedTT=true;
                        TTState.bedTemp = jsonObject.getFloat("temp");
                    }
                    else if(message.getPayload().toString().contains("Balcony")){
                        balconyService.update(JSON.toJavaObject(jsonObject, BalconyTemp.class));
                        TTState.balconyTT=true;
                        TTState.balconyTemp = jsonObject.getFloat("temp");
                    }
                    System.out.println("save data");
                }
            }
        };
    }
}
