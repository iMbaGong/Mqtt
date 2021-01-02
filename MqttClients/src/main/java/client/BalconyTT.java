package client;

public class BalconyTT extends TemperatureTransducer{
    public BalconyTT() {
        clientId = "Balcony";
    }

    public static void main(String[] args) {
        BalconyTT balconyTT = new BalconyTT();
        balconyTT.start();
    }
}
