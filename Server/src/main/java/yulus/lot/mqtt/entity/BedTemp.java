package yulus.lot.mqtt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "bedroomtemp")
@JsonIgnoreProperties({"handle","hibernateLazyInitializer"})
public class BedTemp implements Comparable<BedTemp>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Date date;
    float temp;

    @Override
    public int compareTo(BedTemp temperature){
        return this.date.compareTo(temperature.date);
    }

}
