package yulus.lot.mqtt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bathroomtemp")
@JsonIgnoreProperties({"handle","hibernateLazyInitializer"})
public class BathTemp implements Comparable<BathTemp>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Date date;
    float temp;

    @Override
    public int compareTo(BathTemp temperature){
        return this.date.compareTo(temperature.date);
    }
}
