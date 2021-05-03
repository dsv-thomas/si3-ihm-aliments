package com.dsv.td1.si3_ihm_aliments.helpers;

import android.content.Context;
import android.content.ContextWrapper;
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

    public static String getDirName(Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        String directoryName = (cw.getDir("imageDir", Context.MODE_PRIVATE)).getPath();
        return directoryName;
    }
}
