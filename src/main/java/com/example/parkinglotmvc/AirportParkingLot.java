package com.example.parkinglotmvc;

public class AirportParkingLot implements IParkingLot {
    // £7 per hour for up to 7 hours, £50 for >7hrs/per day, mins round up
    @Override
    public int calculateFee(Ticket ticket) {
        int days = ticket.getDays();
        int hours = ticket.getHours();
        int minutes = ticket.getMinutes();

        int hourlyCharge = 7;
        int dailyCharge = 50;
        int hourlyChargeCap = 7;

        int fee = 0;
        fee+= days*dailyCharge;

            if (hours > hourlyChargeCap || (hours == hourlyChargeCap && minutes > 0)) {
                fee += dailyCharge;
            }

            else {
                fee += hours*hourlyCharge;

                if (minutes > 0) {
                    fee+=hourlyCharge;
                }
            }
        return fee;
    }
}
