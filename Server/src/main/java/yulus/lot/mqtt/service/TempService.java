package yulus.lot.mqtt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yulus.lot.mqtt.dao.TempDAO;
import yulus.lot.mqtt.entity.Temperature;

@Service
public class TempService {
    @Autowired
    TempDAO tempDAO;

    public void update(Temperature data){
        tempDAO.save(data);
    }
}
