package com.example.parkinglotmvc;

public class ParkingLotFactory {

    public IParkingLot createParkingLot(String type) {
        switch(type) {
            case "Long stay parking" -> {
                return new LongStayParkingLot();
            }
            case "Airport parking" -> {
                return new AirportParkingLot();
            }
            case "Multi-storey parking" -> {
                return new MultiStoreyParkingLot();
            }
            default -> {
                return new ShortStayParkingLot();
            }
        }
    }
}
