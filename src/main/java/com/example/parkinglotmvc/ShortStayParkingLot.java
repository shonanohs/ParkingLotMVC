package com.example.parkinglotmvc;

public class ShortStayParkingLot implements IParkingLot {
    // £2 per hour, £50 fine (per day) + standard hourly charge if you stay for more than 4 hours, minutes round up, no charge for 30 mins or less
    @Override
    public int calculateFee(Ticket ticket) {
        int days = ticket.getDays();
        int hours = ticket.getHours();
        int minutes = ticket.getMinutes();

        int fee = 0;

        if (days == 0 && hours == 0 && minutes <= 30) {
            return fee;
        }

        fee+= days*2*24;
        fee+= hours*2;

        if(minutes > 0) {
            fee+= 2;
        }

        if (days > 0 || (hours > 4 && days == 0)) {
            fee+=50*days;
        }

        return fee;
    }
}
