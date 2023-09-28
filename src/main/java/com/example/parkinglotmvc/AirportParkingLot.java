package com.example.parkinglotmvc;

public class AirportParkingLot implements IParkingLot {
    // £7 per hour for up to 7 hours, £50 for >7hrs/per day, mins round up
    @Override
    public int calculateFee(Ticket ticket) {
        int days = ticket.getDays();
        int hours = ticket.getHours();
        int minutes = ticket.getMinutes();

        int fee = 0;
        fee+= days*50;

            if (hours > 7 || (hours == 7 && minutes > 0)) {
                fee += 50;
            }

            else {
                fee += hours*7;

                if (minutes > 0) {
                    fee+=7;
                }
            }
        return fee;
    }
}
