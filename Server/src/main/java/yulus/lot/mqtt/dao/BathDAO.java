package yulus.lot.mqtt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yulus.lot.mqtt.entity.BathTemp;

import java.util.Date;
import java.util.List;

public interface BathDAO extends JpaRepository<BathTemp,Integer> {
    public List<BathTemp> findAllByDateGreaterThan(Date time);
}
