package client;

public class DiningRoomTT extends TemperatureTransducer{
    public DiningRoomTT() {
        clientId = "DiningRoom";
    }

    public static void main(String[] args) {
        DiningRoomTT diningRoomTT = new DiningRoomTT();
        diningRoomTT.start();
    }
}
