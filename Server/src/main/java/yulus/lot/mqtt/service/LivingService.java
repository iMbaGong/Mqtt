package yulus.lot.mqtt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yulus.lot.mqtt.dao.LivingDAO;
import yulus.lot.mqtt.entity.LivingTemp;

import java.util.Date;
import java.util.List;

@Service
public class LivingService {

    @Autowired
    LivingDAO livingDAO;

    public void update(List<LivingTemp> temps){
        livingDAO.saveAll(temps);
    }

    public void update(LivingTemp temp){
        livingDAO.save(temp);
    }

    public List<LivingTemp> getByDate(String type){
        long day = 1000*3600*24;
        long week = day*7;
        long month = week*31;
        long year = day*365;
        Date now = new Date();
        Date time = new Date();
        switch (type){
            case "day":{
                time.setTime(now.getTime()-day);
                break;
            }
            case "week":{
                time.setTime(now.getTime()-week);
                break;
            }
            case "month":{
                time.setTime(now.getTime()-month);
                break;
            }
            case "year":{
                time.setTime(now.getTime()-year);
                break;
            }
            case "years":{
                time.setTime(now.getTime()-year*3);
                break;
            }
        }
        return livingDAO.findAllByDateGreaterThan(time);
    }
}
