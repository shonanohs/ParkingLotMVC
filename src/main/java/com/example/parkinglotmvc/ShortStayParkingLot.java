package com.example.parkinglotmvc;

public class ShortStayParkingLot implements IParkingLot {
    // £2 per hour, £50 fine (per day) + standard hourly charge if you stay for more than 4 hours,
    // minutes round up, no charge for 30 mins or less
    @Override
    public int calculateFee(Ticket ticket) {
        int days = ticket.getDays();
        int hours = ticket.getHours();
        int minutes = ticket.getMinutes();

        int hourlyCharge = 2;
        int dailyFine = 50;
        int stayDurationCap = 4;
        int minsFreeOfCharge = 30;

        int fee = 0;

        if (days == 0 && hours == 0 && minutes <= minsFreeOfCharge) {
            return fee;
        }

        fee+= days*hourlyCharge*24;
        fee+= hours*hourlyCharge;

        if(minutes > 0) {
            fee+= hourlyCharge;
        }

        if (days > 0 || (hours > stayDurationCap && days == 0)) {
            fee+=dailyFine*days;
        }

        return fee;
    }
}
