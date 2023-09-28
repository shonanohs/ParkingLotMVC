package com.example.parkinglotmvc;

public class MultiStoreyParkingLot implements IParkingLot {
    // £5 flat fee + £3 p/h add-on, minutes round up
    @Override
    public int calculateFee(Ticket ticket) {
        int days = ticket.getDays();
        int hours = ticket.getHours();
        int minutes = ticket.getMinutes();

        int fee = 5;

        fee+= days*3*24;
        fee += hours*3;
            if (minutes > 0) {
                fee+=3;
            }
        return fee;
    }
}
