package yulus.lot.mqtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yulus.lot.mqtt.entity.Temperature;

import com.alibaba.fastjson.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yulus.lot.mqtt.entity.TempData;

import yulus.lot.mqtt.gateway.MqttGateway;
import yulus.lot.mqtt.service.TempService;

import javax.annotation.Resource;

import java.util.*;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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