package com.dsv.td1.si3_ihm_aliments.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.CalendarContract;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dsv.td1.si3_ihm_aliments.controller.IPermissionRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarHelper {

    private CalendarHelper() {}

    private static SimpleDateFormat dayAndTimeFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
    private static SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");


    public static Intent addEventToCalendar(Activity activity, String lieu, Date startDate, Date endDate) {
        return addEventToCalendar(activity, lieu, startDate, endDate, "");
    }

    public static Intent addEventToCalendar(Activity activity, String lieu,
                                            Date startDate, Date endDate, String description) {
        int permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CALENDAR);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_CALENDAR}, IPermissionRequest.CAL_WRITE_REQ);
        }

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.Events.TITLE, "Pickup point");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, lieu);
        intent.putExtra(CalendarContract.Events.ALL_DAY, "false");
        intent.putExtra(CalendarContract.Events.CALENDAR_TIME_ZONE, "Europe/Paris");
        intent.putExtra(CalendarContract.Events.DTSTART, startDate.getTime());
        intent.putExtra(CalendarContract.Events.DTEND, endDate.getTime());
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description);

        return intent;
    }


    public static Date dayAndTime(String day, String time) {
        try {
            return dayAndTimeFormat.parse(day+"-"+time);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static Date time(String time) {
        try {
            return timeFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static Date day(String day) {
        try {
            return dayFormat.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static String formatDate(Date date) {
        return dayFormat.format(date);
    }

    public static String formatTime(Date time) {
        return timeFormat.format(time);
    }
}
