package com.yang.linus.Utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by linusyang on 16-12-5.
 */

public class Util {

    public static Bitmap getScaledBitmap(Bitmap bitmap, int width, int height) {
        Bitmap finalBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(finalBitmap);
        BitmapDrawable bd = new BitmapDrawable(bitmap);
        bd.setBounds(0, 0, width, height);
        bd.draw(canvas);
        bd = null;
        return finalBitmap;
    }


    public static float clamp(float x, float min, float max) {
        if (x > max) return max;
        if (x < min) return min;
        return x;
    }

}
