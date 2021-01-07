package yulus.lot.mqtt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "balcony")
@JsonIgnoreProperties({"handle","hibernateLazyInitializer"})
public class BalconyTemp  implements Comparable<BalconyTemp>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Date date;
    float temp;

    @Override
    public int compareTo(BalconyTemp temperature){
        return this.date.compareTo(temperature.date);
    }
}
