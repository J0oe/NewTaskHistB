package com.ex.admin.newtaskhist;

import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

import java.net.URL;
import java.util.Calendar;

import static com.ex.admin.newtaskhist.MainActivity.statusImage;

/**
 * Created by Admin on 25.02.2018.
 */

public class Worker implements IWorker {
    @Override
    public ContentValues saveImageDate(String IMAGE_STATUS, String IMAGE_TIME, String IMAGE_URL, String urlImage) {
        ContentValues cv = new ContentValues();
        Calendar c = Calendar.getInstance();
        int hourTime = c.get(Calendar.HOUR_OF_DAY);
        int minuteTime = c.get(Calendar.MINUTE);
        int secondTime = c.get(Calendar.SECOND);

        String trueMin = null;
        String trueSec = null;

        if (String.valueOf(minuteTime).length() < 2) {
            trueMin = "0" + String.valueOf(minuteTime);
        }
        if (String.valueOf(minuteTime).length() >= 2) {
            trueMin =  String.valueOf(minuteTime);
        }

        if (String.valueOf(secondTime).length() < 2) {
            trueSec = "0" + String.valueOf(secondTime);
        }
        if (String.valueOf(secondTime).length() >= 2) {
            trueSec =  String.valueOf(secondTime);
        }


        String time = String.valueOf(hourTime) + "-" + trueMin + "-" + trueSec;
        cv.put(IMAGE_STATUS, MainActivity.statusImage);
        cv.put(IMAGE_TIME, time);
        cv.put(IMAGE_URL, urlImage);

        return cv;
    }


}

