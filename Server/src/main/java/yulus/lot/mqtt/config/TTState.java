package yulus.lot.mqtt.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TTState {
    static public boolean livingTT = false;
    static public boolean bedTT = false;
    static public boolean diningTT = false;
    static public boolean bathTT = false;
    static public boolean balconyTT = false;
    static public float livingTemp = 0;
    static public float bedTemp = 0;
    static public float diningTemp = 0;
    static public float bathTemp = 0;
    static public float balconyTemp = 0;
}
