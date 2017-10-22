package com.yarten.sgbasic;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Size;

/**
 * Created by yarten on 2017/10/8.
 */

public class Screen
{
    public static void init(Context context)
    {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        pixelWidth = dm.widthPixels;
        pixelHeight = dm.heightPixels;
    }

    public static class Size
    {
        Size(){this(0, 0);}

        Size(int width, int height)
        {
            this.width = width;
            this.height = height;
        }

        public int width;
        public int height;
    }

    private static int pixelWidth, pixelHeight;

    public static Size getResolution()
    {
        return new Size(pixelWidth, pixelHeight);
    }
}
