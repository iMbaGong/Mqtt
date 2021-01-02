package yulus.lot.mqtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yulus.lot.mqtt.gateway.MqttGateway;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class MqttController {

    @Resource
    private MqttGateway mqttGateway;

    @RequestMapping("/sendMqtt")
    public String sendMqtt(String sendData, String topic) {
//        mqttGateway.sendToMqtt(sendData,"hello");
        mqttGateway.sendToMqtt(sendData, topic);
        return "发送内容：" + sendData + "成功----" + "主题：" + topic;
    }
}