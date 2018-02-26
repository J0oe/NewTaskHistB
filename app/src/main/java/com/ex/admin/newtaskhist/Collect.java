package com.ex.admin.newtaskhist;

import android.content.ContentValues;
import android.net.Uri;

/**
 * Created by Admin on 25.02.2018.
 */

public class Collect implements ICollect {
    Worker worker = new Worker();

    @Override
    public ContentValues newImage(String IMAGE_STATUS, String IMAGE_TIME, String IMAGE_URL, String urlImage) {
        return worker.saveImageDate(IMAGE_STATUS, IMAGE_TIME, IMAGE_URL, urlImage);
    }


}
