package yulus.lot.mqtt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yulus.lot.mqtt.entity.Temperature;

import java.util.Date;
import java.util.List;

public interface TempDAO extends JpaRepository<Temperature,Integer> {
    public List<Temperature> findAllByLocationAndDateGreaterThan(String location, Date date);
}
