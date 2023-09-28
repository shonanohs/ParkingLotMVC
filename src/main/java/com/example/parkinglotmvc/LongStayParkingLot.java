package com.example.parkinglotmvc;

public class LongStayParkingLot implements IParkingLot {
    // £2 per hour, minutes always round up, max charge per day £15, no charge for 30 mins or less
    @Override
    public int calculateFee(Ticket ticket) {
        int days = ticket.getDays();
        int hours = ticket.getHours();
        int minutes = ticket.getMinutes();

        int fee = 0;
        if (days == 0 && hours == 0 && minutes <= 30) {
            return fee;
        }
        else {
            fee+= days*15;

            if (hours > 7 || (hours == 7 && minutes > 0)) {
                fee += 15;
            }

            else {
                fee += hours*2;

                if (minutes > 0) {
                    fee+=2;
                }
            }
        }
        return fee;
    }
}
