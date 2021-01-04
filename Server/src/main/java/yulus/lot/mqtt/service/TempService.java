package yulus.lot.mqtt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yulus.lot.mqtt.dao.TempDAO;
import yulus.lot.mqtt.entity.Temperature;

import java.util.Date;
import java.util.List;

@Service
public class TempService {
    @Autowired
    TempDAO tempDAO;

    public void update(Temperature data){
        tempDAO.save(data);
    }

    public List<Temperature> getByLocation(String location){
        Date now = new Date();
        now.setTime(now.getTime()-1000*3600*24*14);
        return tempDAO.findAllByLocationAndDateGreaterThan(location,now);
    }
}
