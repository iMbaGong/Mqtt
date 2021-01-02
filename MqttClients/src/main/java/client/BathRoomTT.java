package client;

public class BathRoomTT extends TemperatureTransducer{
    public BathRoomTT() {
        clientId = "BathRoomTT";
    }

    public static void main(String[] args) {
        BathRoomTT bathRoomTT = new BathRoomTT();
        bathRoomTT.start();
    }
}
