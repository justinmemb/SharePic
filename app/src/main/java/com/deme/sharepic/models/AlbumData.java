package com.deme.sharepic.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.deme.sharepic.utils.AppUtils;

import static com.deme.sharepic.utils.Constants.THUMBNAIL_HEIGHT;
import static com.deme.sharepic.utils.Constants.THUMBNAIL_SIZE;

/**
 * Created by Dima on 9/16/2018.
 */

public class AlbumData {
    String path;
    long id;
    Bitmap bitmap;

    public AlbumData(String path, long id, Bitmap bitmap) {
        this.path = path;
        this.id = id;
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getPath() {
        return path;
    }

    public long getId() {
        return id;
    }
}
