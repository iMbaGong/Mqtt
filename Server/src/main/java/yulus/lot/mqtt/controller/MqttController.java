package yulus.lot.mqtt.controller;

import com.alibaba.fastjson.JSONObject;
import javafx.print.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yulus.lot.mqtt.entity.Temperature;
import yulus.lot.mqtt.gateway.MqttGateway;
import yulus.lot.mqtt.service.TempService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class MqttController {

    @Resource
    private MqttGateway mqttGateway;
    @Autowired
    TempService tempService;

    @CrossOrigin
    @GetMapping("/currentTemperature")
    public String getCurTemp(@RequestParam(name = "location") String location) {
        switch (location) {
            case "LivingRoom":
            case "BedRoom":
            case "DiningRoom":
            case "BathRoom":
            case "balcony":
                break;
            default:
                return "错误的请求信息";
        }
        mqttGateway.sendToMqtt(location, "LoT/Control");
        return "请求成功";
    }

    @CrossOrigin
    @GetMapping("/allTemperature")
    public List<Temperature> getAllTemp(@RequestParam(name = "location") String location) {
        List<Temperature> temps = tempService.getByLocation(location);
        predict(temps,location);
        Collections.sort(temps);
        return temps;
    }

    @GetMapping("/init")
    public void init(){
        Date cur = new Date();
        long twMin = 1000*60*20;
        Random random = new Random();
        for(int i=0;i<7*24*3;i++){
            System.out.println(i);
            Date time = new Date(cur.getTime()-twMin*i);
            Temperature temperature = new Temperature();
            temperature.setTemp(random.nextFloat() * 40 - 10);
            temperature.setLocation("Balcony");
            temperature.setDate(time);
            tempService.update(temperature);
        }
    }


    void predict(List<Temperature> temps,String location) {
        float preTemp[] = new float[72];
        for (Temperature t :
                temps) {
            int index = t.getDate().getHours() * 3 + t.getDate().getMinutes() / 20;
            if (preTemp[index] == 0)
                preTemp[index] = t.getTemp();
            else
                preTemp[index] = (preTemp[index] + t.getTemp()) / 2;
        }
        Date now = new Date();
        int index = now.getHours()*3+now.getMinutes()/20;
        for(int i=0;i<72;i++){
            Temperature temperature = new Temperature();
            temperature.setDate(new Date(now.getTime()+i*1000*60*20));
            temperature.setLocation(location);
            temperature.setTemp(preTemp[(index+i)%72]);
            temps.add(temperature);
        }
    }
}