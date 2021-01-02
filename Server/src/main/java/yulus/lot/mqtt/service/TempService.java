package yulus.lot.mqtt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yulus.lot.mqtt.dao.TempDAO;

@Service
public class TempService {
    @Autowired
    TempDAO tempDAO;

    public void update(String data){
        
    }
}
