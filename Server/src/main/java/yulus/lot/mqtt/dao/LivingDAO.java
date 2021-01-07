package yulus.lot.mqtt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yulus.lot.mqtt.entity.LivingTemp;

import java.util.Date;
import java.util.List;

public interface LivingDAO extends JpaRepository<LivingTemp,Integer> {
    public List<LivingTemp> findAllByDateGreaterThan(Date time);
}
