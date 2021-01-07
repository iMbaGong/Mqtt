package yulus.lot.mqtt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yulus.lot.mqtt.entity.BedTemp;

import java.util.Date;
import java.util.List;

public interface BedDAO extends JpaRepository<BedTemp,Integer> {
    public List<BedTemp> findAllByDateGreaterThan(Date time);
}
