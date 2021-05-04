package com.dsv.td1.si3_ihm_aliments.consumer;

import org.osmdroid.util.GeoPoint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PickupPoint {
    private String place;
    private Date date;
    private Date scheduleStart;
    private Date scheduleEnd;
    private GeoPoint geoPoint;

    public PickupPoint(String place, Date date, Date scheduleS, Date scheduleE) {
        this.place = place;
        this.date = date;
        this.scheduleStart = scheduleS;
        this.scheduleEnd = scheduleE;
    }

    public PickupPoint(String place, Date date, Date scheduleStart, Date scheduleEnd, GeoPoint geoPoint) {
        this.place = place;
        this.date = date;
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
        this.geoPoint = geoPoint;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return simpleDateFormat.format(date) ;
    }

    public String getSchedule() {
        String schedule = new String();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm", Locale.FRANCE);
        schedule = "De "+ simpleDateFormat1.format(scheduleStart) + " à " + simpleDateFormat1.format(scheduleEnd);
        return schedule;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    @Override
    public String toString() {
        return getPlace();
    }
}
