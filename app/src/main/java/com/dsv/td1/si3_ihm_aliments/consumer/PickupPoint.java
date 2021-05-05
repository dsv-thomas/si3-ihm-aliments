package com.dsv.td1.si3_ihm_aliments.consumer;

import com.dsv.td1.si3_ihm_aliments.producer.Producer;

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
    private Producer producer;


    public PickupPoint(String place, Date date, Date scheduleStart, Date scheduleEnd, GeoPoint geoPoint) {
        this.place = place;
        this.date = date;
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
        this.geoPoint = geoPoint;
    }

    public PickupPoint(Producer producer, String place, Date date, Date scheduleStart, Date scheduleEnd, GeoPoint geoPoint) {
        this.producer = producer;
        this.place = place;
        this.date = date;
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
        this.geoPoint = geoPoint;
    }

    public Producer getProducer() {
        return producer;
    }

    public String getPlace() {
        return place;
    }

    public String getDateString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return simpleDateFormat.format(date) ;
    }

    public Date getDate() {
        return this.date;
    }

    public Date getTimeStart() {
        return this.scheduleStart;
    }

    public Date getTimeEnd() {
        return this.scheduleEnd;
    }

    public String getSchedule() {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm", Locale.FRANCE);
        String schedule = "De " + simpleDateFormat1.format(scheduleStart) + " à " + simpleDateFormat1.format(scheduleEnd);
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
