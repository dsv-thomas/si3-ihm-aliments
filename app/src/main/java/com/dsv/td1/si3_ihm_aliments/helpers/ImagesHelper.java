package com.dsv.td1.si3_ihm_aliments.helpers;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.dsv.td1.si3_ihm_aliments.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public final class ImagesHelper {

    public static Bitmap loadImageFromStorage(Context context, String directoryName, String filename) {
        if (context != null) {
            try {
                File f = new File(directoryName, filename + ".jpg");
                if (!f.exists()) {
                    return BitmapFactory.decodeResource(context.getResources(), R.drawable.nopicture);
                }
                return BitmapFactory.decodeStream(new FileInputStream(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getDirName(Context context) {
        Log.d("CONTEXT", String.valueOf(context));
        ContextWrapper cw = new ContextWrapper(context);
        if (context != null) {
            return cw.getDir("imageDir", Context.MODE_PRIVATE).getPath();
        }
        return "";
    }
}
