package client;

public class BedRoomTT extends TemperatureTransducer{
    public BedRoomTT(){
        clientId = "BedRoom";
        tempFunc = new float[]{
                0,0,0,0,
                0,0,0,0,
                0,0,0,0,
                0,0,0,0,
                0,0,0,0,
                0,0,0,0,
                -3,-3,-3,-3,
                -2,-2,-2,-2,
                -2,-2,-2,-2,
                0,0,0,0,
                0,0,0,0,
                0,0,0,0,
                4,4,4,4,
                4,4,4,4,
                3,3,3,3,
                0,0,0,0,
                0,0,0,0,
                0,0,0,0,
                0,0,0,0,
                0,0,0,0,
                0,0,0,0,
                1,2,3,4,
                3,3,3,3,
                3,3,3,3
        };
    }

    public static void main(String[] args) {
        BedRoomTT bedRoomTT = new BedRoomTT();
        bedRoomTT.start();
    }
}
