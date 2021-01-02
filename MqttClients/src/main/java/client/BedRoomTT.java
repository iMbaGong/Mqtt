package client;

public class BedRoomTT extends TemperatureTransducer{
    public BedRoomTT(){
        clientId = "BedRoom";
    }

    public static void main(String[] args) {
        BedRoomTT bedRoomTT = new BedRoomTT();
        bedRoomTT.start();
    }
}
