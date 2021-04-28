package com.dsv.td1.si3_ihm_aliments.consumer;

import java.util.Date;

public class PickupPoint {
    private String place;
    private Date date;
    private Date schedule;

    public PickupPoint(String place, Date date, Date schedule) {
        this.place = place;
        this.date = date;
        this.schedule = schedule;
    }

    public String getPlace() {
        return place;
    }

    public Date getDate() {
        return date;
    }

    public Date getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return getPlace();
    }
}
