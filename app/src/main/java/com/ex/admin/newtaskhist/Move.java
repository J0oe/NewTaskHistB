package com.ex.admin.newtaskhist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static com.ex.admin.newtaskhist.MainActivity.mIcon11;

/**
 * Created by Admin on 24.02.2018.
 */

public class Move {
    interface Moved {
        void runForset();
    }

    Moved myUser;

    public void registerCallBack(Moved moved) {
        this.myUser = moved;
    }


    void run(String urlImage, ImageView bmImage, Context context) {

        if (NetworkManager.isNetworkAvailable(context)) {
            new DownloadImageTask(bmImage).execute(urlImage);
        } else {
            MainActivity.statusImage = "3";
            myUser.runForset();

        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                MainActivity.statusImage = "1";

            } catch (Exception e) {
                MainActivity.statusImage = "2";
            }

            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            myUser.runForset();
        }
    }

    public static class NetworkManager {

        public static boolean isNetworkAvailable(Context context) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            } else {
                return false;
            }
        }
    }
}




