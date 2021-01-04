package yulus.lot.mqtt.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yulus.lot.mqtt.entity.TempData;
import yulus.lot.mqtt.gateway.MqttGateway;

import javax.annotation.Resource;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @GetMapping("/readJson")
    public String readJson() {
        String jsonStr = "";
        try {
            File jsonFile = new File("src/main/resources/temp.json");
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }


        JSONArray jsonArray = JSONArray.parseArray(jsonStr);
        for (int i = 0; i < jsonArray.size(); i++) {
            TempData tempData = jsonArray.getJSONObject(i).toJavaObject(TempData.class);
            int h=0,l=0;
            String regEx="-?[1-9]\\d*";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(tempData.getLTemp());
            if(m.find()){
                l = Integer.parseInt(m.group(0));
            }
            m = p.matcher(tempData.getHTemp());
            if(m.find()){
                h = Integer.parseInt(m.group(0));
            }
            tempData.setTemp((h+l)/2);
            System.out.println(tempData.getTemp());
        }
        return "请求成功";
    }


}