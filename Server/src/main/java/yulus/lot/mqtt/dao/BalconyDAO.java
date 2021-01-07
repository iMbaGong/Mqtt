package yulus.lot.mqtt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yulus.lot.mqtt.entity.BalconyTemp;

import java.util.Date;
import java.util.List;

public interface BalconyDAO extends JpaRepository<BalconyTemp,Integer> {
    public List<BalconyTemp> findAllByDateGreaterThan(Date time);
}
