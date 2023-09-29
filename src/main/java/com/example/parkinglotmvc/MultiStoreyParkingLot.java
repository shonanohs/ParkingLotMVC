package com.example.parkinglotmvc;

public class MultiStoreyParkingLot implements IParkingLot {
    // £5 flat fee + £3 p/h add-on, minutes round up
    @Override
    public int calculateFee(Ticket ticket) {
        int days = ticket.getDays();
        int hours = ticket.getHours();
        int minutes = ticket.getMinutes();

        int flatFee = 5;
        int hourlyCharge = 2;

        int fee = flatFee;
        fee+= days*hourlyCharge*24;
        fee += hours*hourlyCharge;
        if (minutes > 0) {
            fee+=hourlyCharge;
        }

        return fee;
    }
}
