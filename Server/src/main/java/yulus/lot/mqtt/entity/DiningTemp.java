package yulus.lot.mqtt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "diningroomtemp")
@JsonIgnoreProperties({"handle","hibernateLazyInitializer"})
public class DiningTemp implements Comparable<DiningTemp>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Date date;
    float temp;

    @Override
    public int compareTo(DiningTemp temperature){
        return this.date.compareTo(temperature.date);
    }
}
