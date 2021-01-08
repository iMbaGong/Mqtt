package yulus.lot.mqtt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
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
