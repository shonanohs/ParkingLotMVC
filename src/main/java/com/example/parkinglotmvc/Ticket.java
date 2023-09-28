package com.example.parkinglotmvc;

import java.time.Duration;
import java.time.LocalDateTime;

    public class Ticket {
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private int days;
    private int hours;
    private int minutes;

    public Ticket(LocalDateTime entryTime, LocalDateTime exitTime) {
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public void calculateDuration() {
        Duration duration = Duration.between(entryTime, exitTime);
        days = (int) duration.toDaysPart();
        hours = duration.toHoursPart();
        minutes = duration.toMinutesPart();
    }

    public int getDays() {
        return days;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }
}
