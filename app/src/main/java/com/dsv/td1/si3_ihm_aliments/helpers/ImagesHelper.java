package com.dsv.td1.si3_ihm_aliments.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImagesHelper {

    public static Bitmap loadImageFromStorage(String directoryName, String filename) {
        try {
            File f = new File(directoryName, filename + ".jpg");
            return BitmapFactory.decodeStream(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
