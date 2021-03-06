package yulus.lot.mqtt.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yulus.lot.mqtt.config.TTState;
import yulus.lot.mqtt.entity.*;

import com.alibaba.fastjson.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import yulus.lot.mqtt.gateway.MqttGateway;
import yulus.lot.mqtt.service.*;

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
    LivingService livingService;
    @Autowired
    BedService bedService;
    @Autowired
    BathService bathService;
    @Autowired
    DiningService diningService;
    @Autowired
    BalconyService balconyService;

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
    @GetMapping("/overview")
    public OverView overView(){
        List<Boolean> states = new ArrayList<>();
        states.add(TTState.livingTT);
        states.add(TTState.diningTT);
        states.add(TTState.bedTT);
        states.add(TTState.bathTT);
        states.add(TTState.balconyTT);
        List<Float> temps = new ArrayList<>();
        temps.add(TTState.livingTemp);
        temps.add(TTState.diningTemp);
        temps.add(TTState.bedTemp);
        temps.add(TTState.bathTemp);
        temps.add(TTState.balconyTemp);
        return new OverView(temps,states);
    }

    @CrossOrigin
    @GetMapping("/allTemperature")
    public Result getAllTemp(@RequestParam(name = "location") String location,
                           @RequestParam(name = "type") String type) {
        Random random = new Random();
        switch (location) {
            case "LivingRoom": {
                List<LivingTemp> list = livingService.getByDate(type);
                List<LivingTemp> pre = new ArrayList<>(list);
                Date now = new Date();
                Date last = list.get(list.size()-1).getDate();
                long tmp = now.getTime()-last.getTime();
                for (LivingTemp temp:
                     pre) {
                    temp.setDate(new Date(tmp+temp.getDate().getTime()));
                    temp.setTemp(temp.getTemp()+random.nextFloat()*5-5f);
                }
                Collections.sort(list);
                return new Result(list,pre);
            }
            case "BedRoom":{
                List<BedTemp> list = bedService.getByDate(type);
                List<BedTemp> pre = new ArrayList<>(list);
                Date now = new Date();
                Date last = list.get(list.size()-1).getDate();
                long tmp = now.getTime()-last.getTime();
                for (BedTemp temp:
                        pre) {
                    temp.setDate(new Date(tmp+temp.getDate().getTime()));
                    temp.setTemp(temp.getTemp()+random.nextFloat()*5-5f);
                }
                Collections.sort(list);
                return new Result(list,pre);
            }

            case "DiningRoom":{
                List<DiningTemp> list = diningService.getByDate(type);
                List<DiningTemp> pre = new ArrayList<>(list);
                Date now = new Date();
                Date last = list.get(list.size()-1).getDate();
                long tmp = now.getTime()-last.getTime();
                for (DiningTemp temp:
                        pre) {
                    temp.setDate(new Date(tmp+temp.getDate().getTime()));
                    temp.setTemp(temp.getTemp()+random.nextFloat()*5-5f);
                }
                Collections.sort(list);
                return new Result(list,pre);
            }
            case "BathRoom":{
                List<BathTemp> list = bathService.getByDate(type);
                List<BathTemp> pre = new ArrayList<>(list);
                Date now = new Date();
                Date last = list.get(list.size()-1).getDate();
                long tmp = now.getTime()-last.getTime();
                for (BathTemp temp:
                        pre) {
                    temp.setDate(new Date(tmp+temp.getDate().getTime()));
                    temp.setTemp(temp.getTemp()+random.nextFloat()*5-5f);
                }
                Collections.sort(list);
                return new Result(list,pre);
            }
            case "balcony":{
                List<BalconyTemp> list = balconyService.getByDate(type);
                List<BalconyTemp> pre = new ArrayList<>(list);
                Date now = new Date();
                Date last = list.get(list.size()-1).getDate();
                long tmp = now.getTime()-last.getTime();
                for (BalconyTemp temp:
                        pre) {
                    temp.setDate(new Date(tmp+temp.getDate().getTime()));
                    temp.setTemp(temp.getTemp()+random.nextFloat()*5-5f);
                }
                Collections.sort(list);
                return new Result(list,pre);
            }
            default:
                return null;
        }
    }


    <T>  void predict(List<T> temps, String type) {
//        float preTemp[];
//        switch (type){
//            case "day":{
//                preTemp = new float[96];
//                for (T t :
//                        temps) {
//                    int index = t.getDate().getHours() * 3 + t.getDate().getMinutes() / 20;
//                    if (preTemp[index] == 0)
//                        preTemp[index] = t.getTemp();
//                    else
//                        preTemp[index] = (preTemp[index] + t.getTemp()) / 2;
//                }
//                Date now = new Date();
//                int index = now.getHours() * 3 + now.getMinutes() / 20;
//                for(int i=0;i<96;i++){
//                    Temperature temperature = new Temperature();
//                    temperature.setDate(new Date(now.getTime() + i * 1000 * 60 * 20));
//                    temperature.setTemp(preTemp[(index + i) % 72]);
//                    temps.add((T)temperature);
//                }
//                break;
//            }
//            case "week":{
//            }
//            case "month":{
//                preTemp = new float[96*31];
//                for (T t :
//                        temps) {
//                    int index = t.getDate().getMinutes() / 20 + t.getDate().getHours() * 3 +(t.getDate().getDay()-1)*96;
//                    if (preTemp[index] == 0)
//                        preTemp[index] = t.getTemp();
//                    else
//                        preTemp[index] = (preTemp[index] + t.getTemp()) / 2;
//                }
//                break;
//            }
//            case "year":
//            case "years":{
//                preTemp = new float[96*365];
//                break;
//            }
//            default:
//                preTemp = new float[96*365];
//        }
    }

    @GetMapping("/readJson")
    public String initAll() {
        JSONArray allData = readJson("temp.json");
        JSONArray livingData = readJson("BathRoom.json");

        float livingArr[] = new float[96];
        for (int i = 0; i < 96; i++) {
            livingArr[i] = livingData.getFloat(i);
        }
        long ftMin = 1000 * 60 * 15;
        Random random = new Random();
        for (int i = 0; i < allData.size()/36; i++) {
            TempData tempData = allData.getJSONObject(i).toJavaObject(TempData.class);
            int h = 0, l = 0;
            String regEx = "-?[1-9]\\d*";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(tempData.getLTemp());
            if (m.find()) {
                l = Integer.parseInt(m.group(0));
            }
            m = p.matcher(tempData.getHTemp());
            if (m.find()) {
                h = Integer.parseInt(m.group(0));
            }
            tempData.setTemp((h + l) / 2);
            long time = tempData.getDate().getTime();
            List<BathTemp> temps = new ArrayList<>();
            for (int j = 0; j < 96; j++) {
                BathTemp temp = new BathTemp();
                temp.setDate(new Date(time + ftMin * j));
                temp.setTemp(tempData.getTemp() + livingArr[j] + random.nextFloat() * 2 - 1.0f);
                temps.add(temp);
                System.out.println(i * 96 + j);
            }
            bathService.update(temps);
        }
        return "请求成功";
    }


    JSONArray readJson(String filename) {
        String jsonStr = "";
        try {
            File jsonFile = new File("src/main/resources/" + filename);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
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
        return JSONArray.parseArray(jsonStr);
    }

    @Data
    @AllArgsConstructor
    class Result{
        List temp;
        List pre;
    }

    @Data
    @AllArgsConstructor
    class OverView{
        List temp;
        List state;
    }
}