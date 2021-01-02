package yulus.lot.mqtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yulus.lot.mqtt.gateway.MqttGateway;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class MqttController {

    @Resource
    private MqttGateway mqttGateway;

    @GetMapping("/currentTemperature")
    public String sendMqtt(@RequestParam(name = "location")String location) {
        switch (location){
            case "LivingRoom":
            case "BedRoom":
            case "DiningRoom":
            case "BathRoom":
            case "balcony":break;
            default:return "错误的请求信息";
        }
        mqttGateway.sendToMqtt("cur", "LoT/Temperature/"+location);
        return "请求成功";
    }
}