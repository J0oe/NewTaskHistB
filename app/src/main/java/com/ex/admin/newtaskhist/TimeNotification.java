package com.ex.admin.newtaskhist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

public class TimeNotification extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        File folder = new File(Environment.getExternalStorageDirectory().toString() + "/sdcard/BIGDIG/test/B");
        folder.mkdirs();
        OutputStream fOut = null;
        try {
            Calendar c = Calendar.getInstance();
            int hourTime = c.get(Calendar.HOUR_OF_DAY);
            int minuteTime = c.get(Calendar.MINUTE);
            int secondTime = c.get(Calendar.SECOND);

            File file = new File(folder, String.valueOf(hourTime)+"-"+String.valueOf(minuteTime)+"-"+String.valueOf(secondTime)+"-" + ".jpg");
            fOut = new FileOutputStream(file);


            MainActivity.mIcon11.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
            Toast.makeText(context,"Фото сохранено",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
        }
    }
}
