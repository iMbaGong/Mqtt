package yulus.lot.mqtt.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class Temperature implements Comparable<Temperature>{
    int id;
    Date date;
    float temp;

    @Override
    public int compareTo(Temperature temperature){
        return this.date.compareTo(temperature.date);
    }
}
