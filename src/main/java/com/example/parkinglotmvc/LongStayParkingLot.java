package com.example.parkinglotmvc;

public class LongStayParkingLot implements IParkingLot {
    // £2 per hour, minutes always round up, max charge per day £15, no charge for 30 mins or less
    @Override
    public int calculateFee(Ticket ticket) {
        int days = ticket.getDays();
        int hours = ticket.getHours();
        int minutes = ticket.getMinutes();

        int hourlyCharge = 2;
        int maxDailyCharge = 15;
        int minsFreeOfCharge = 30;
        int hourlyChargeCap = 7;

        int fee = 0;
        if (days == 0 && hours == 0 && minutes <= minsFreeOfCharge) {
            return fee;
        }
        else {
            fee+= days*maxDailyCharge;

            if (hours > hourlyChargeCap || (hours == hourlyChargeCap && minutes > 0)) {
                fee += maxDailyCharge;
            }

            else {
                fee += hours*hourlyCharge;

                if (minutes > 0) {
                    fee+=hourlyCharge;
                }
            }
        }

        return fee;
    }
}
