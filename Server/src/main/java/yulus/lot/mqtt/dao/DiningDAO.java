package yulus.lot.mqtt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yulus.lot.mqtt.entity.DiningTemp;

import java.util.Date;
import java.util.List;

public interface DiningDAO extends JpaRepository<DiningTemp,Integer> {
    public List<DiningTemp> findAllByDateGreaterThan(Date time);
}
