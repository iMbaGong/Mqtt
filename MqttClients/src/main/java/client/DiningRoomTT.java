package client;

public class DiningRoomTT extends TemperatureTransducer{
    public DiningRoomTT() {
        clientId = "DiningRoom";
        tempFunc = new float[]{3,4,4,4,
                4,3,3,3,
                4,4,4,2,
                2,3,2,2,
                4,3,2,2,
                2,3,3,2,
                2,2,2,3,
                3,3,2,3,
                5,4,4,4,
                4,5,5,5,
                6,6,5,7,
                8,8,8,7,
                7,8,7,7,
                8,8,8,7,
                8,8,7,7,
                6,7,7,7,
                6,7,8,6,
                5,6,6,6,
                5,6,6,4,
                5,5,4,4,
                4,4,4,3,
                3,2,3,2,
                3,3,3,3,
                3,3,3,3};
    }

    public static void main(String[] args) {
        DiningRoomTT diningRoomTT = new DiningRoomTT();
        diningRoomTT.start();
    }
}
