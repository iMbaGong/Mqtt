package client;

public class BathRoomTT extends TemperatureTransducer{
    public BathRoomTT() {
        clientId = "BathRoomTT";
        tempFunc = new float[]{
                0,0,0,0,
                0,0,0,0,
                0,0,0,0,
                -1,-1,-1,-1,
                -1,0,0,0,
                0,0,0,1,
                3,2,1,1,
                2,2,2,2,
                2,2,3,3,
                3,3,3,3,
                3,3,4,4,
                4,4,4,4,
                4,4,4,4,
                4,4,3,3,
                3,3,3,3,
                3,3,2,2,
                2,2,2,2,
                2,1,1,1,
                1,1,1,1,
                1,1,1,1,
                2,4,6,8,
                8,7,6,5,
                4,3,2,2,
                1,1,0,0
        };
    }

    public static void main(String[] args) {
        BathRoomTT bathRoomTT = new BathRoomTT();
        bathRoomTT.start();
    }
}
