package com.dsv.td1.si3_ihm_aliments.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.CalendarContract;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dsv.td1.si3_ihm_aliments.controller.IPermissionRequest;

import java.util.Calendar;
import java.util.Date;

public class CalendarHelper {

    private CalendarHelper() {}


    public static Intent addEventToCalendar(Activity activity, String lieu, Date day, Date timeStart, Date timeEnd) {
        return addEventToCalendar(activity, lieu, day, timeStart, timeEnd, "");
    }

    public static Intent addEventToCalendar(Activity activity, String lieu, Date day, Date timeStart, Date timeEnd, String description) {
        int permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CALENDAR);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_CALENDAR}, IPermissionRequest.CAL_WRITE_REQ);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(day);

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.Events.TITLE, "Pickup point");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, lieu);
        intent.putExtra(CalendarContract.Events.ALL_DAY, "false");
        intent.putExtra(CalendarContract.Events.CALENDAR_TIME_ZONE, "Europe/Paris");
        intent.putExtra(CalendarContract.Events.DTSTART, cal.getTimeInMillis());
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description);

        return intent;
    }
}
