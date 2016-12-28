package com.example.q.project1;

import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;

import java.io.File;

/**
 * Created by q on 2016-12-27.
 */

public class Tab3Item {
    private String file_path;
    private Uri image_uri;
    private Bitmap image_bitmap;
    private Bitmap image_thumbnail;

    public Tab3Item() {}

    public Tab3Item(String file_path, Uri image_uri, Bitmap image_bitmap, Bitmap image_thumbnail) {
        this.file_path = file_path;
        this.image_uri = image_uri;
        this.image_bitmap = image_bitmap;
        this.image_thumbnail = image_thumbnail;
    }

    public String getFilePath() { return file_path; }

    public void setName(String file_path) { this.file_path = file_path; }

    public Uri getImageUri() { return image_uri; }

    public void setImageUri(Uri image_uri) { this.image_uri = image_uri; }

    public Bitmap getImageBitmap() { return image_bitmap; }

    public void setImage(Bitmap image_bitmap) { this.image_bitmap = image_bitmap; }

    public Bitmap getImageThumbnail() { return image_thumbnail; }

    public void setImageThumbnail(Bitmap image_thumbnail) { this.image_thumbnail = image_thumbnail; }
}
