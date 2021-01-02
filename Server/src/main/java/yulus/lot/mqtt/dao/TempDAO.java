package yulus.lot.mqtt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yulus.lot.mqtt.entity.Temperature;

public interface TempDAO extends JpaRepository<Temperature,Integer> {
}
