package yulus.lot.mqtt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "temperature")
@JsonIgnoreProperties({"handle","hibernateLazyInitializer"})
public class Temperature implements Comparable<Temperature>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "crt_date")
    Date date;
    String location;
    float temp;

    @Override
    public int compareTo(Temperature temperature){
        return this.date.compareTo(temperature.date);
    }
}
