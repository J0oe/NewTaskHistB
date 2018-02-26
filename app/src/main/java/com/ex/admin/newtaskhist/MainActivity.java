package com.ex.admin.newtaskhist;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Time;
import java.util.AbstractList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements Move.Moved {

    static Bitmap mIcon11;

    Move move;
    Collect collect;
    String idImage;
    String urlImage;
    String action;

    static String statusImage;
    final Uri IMAGE_URI = Uri
            .parse("content://task.ImageLooker/dateImage");
    final String IMAGE_URL = "urlImage";
    final String IMAGE_TIME = "timeImage";
    final String IMAGE_STATUS = "statusImage";

    ImageView imageView;

    int tTime = 9;
    TextView textView;
    private AlarmManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.idTimer);
        imageView = findViewById(R.id.idImage);

        collect = new Collect();
        move = new Move();

        if (getIntent().getStringExtra("urlImage") == null) {
            timer.start();
        } else {
            actionApp(getIntent().getStringExtra("urlImage"), getIntent().getStringExtra("action"), getIntent().getStringExtra("idImage"));
        }
    }


    @Override
    public void runForset() {

        if (action.equals("update")) {
            ContentValues cv = new ContentValues();
            cv.put(IMAGE_STATUS, statusImage);
            Uri uri = ContentUris.withAppendedId(IMAGE_URI, Integer.valueOf(idImage));
            getContentResolver().update(uri, cv, null, null);
        }
        if (action.equals("add")) {
            getContentResolver().insert(IMAGE_URI, collect.newImage(IMAGE_STATUS, IMAGE_TIME, IMAGE_URL, urlImage));
        }
        if (action.equals("delete")) {
            String[] _id = {idImage};
            Uri uri = ContentUris.withAppendedId(IMAGE_URI, Integer.valueOf(idImage));
            getContentResolver().delete(uri, null, _id);
            SavePicture();

        }
    }

    CountDownTimer timer = new CountDownTimer(10 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            String time = "приложение не является самостоятельным приложением и будет закрыто через (" + String.valueOf(tTime) + ") секунд";
            textView.setText(time);
            tTime--;
        }

        @Override
        public void onFinish() {
            finish();

        }
    };


    private void SavePicture() {
        Intent intent = new Intent(this, TimeNotification.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        manager = (AlarmManager) getSystemService(
                Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()+10000, pendingIntent);

    }


    public void actionApp(String urlImage, String action, String idImage) {

        this.action = action;
        this.urlImage = urlImage;
        if (idImage != null) {
            this.idImage = idImage;
        }
        move.registerCallBack(this);
        move.run(urlImage, imageView, this);
    }

}
