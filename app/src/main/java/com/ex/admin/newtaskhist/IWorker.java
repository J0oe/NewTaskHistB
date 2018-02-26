package com.ex.admin.newtaskhist;

import android.content.ContentValues;
import android.net.Uri;

/**
 * Created by Admin on 25.02.2018.
 */

public interface IWorker {
    ContentValues saveImageDate(String IMAGE_STATUS, String IMAGE_TIME, String IMAGE_URL, String urlImage);


}
