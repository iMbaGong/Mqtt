package yulus.lot.mqtt.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class TempData {
    Date date;
    String hTemp;
    String lTemp;
    int temp;
}
