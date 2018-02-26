package com.ex.admin.newtaskhist;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

/**
 * Created by Admin on 25.02.2018.
 */

public interface ICollect {
    ContentValues newImage(String IMAGE_STATUS, String IMAGE_TIME, String IMAGE_URL, String urlImage);

}
