package com.zzb.memoryleak.leaks;

import android.graphics.drawable.Drawable;

/**
 * Created by ZZB on 2016/8/16.
 */
public class Singleton {
    public static Singleton INSTANCE = new Singleton();
    private Drawable mCacheDrawable;

    public void cache(Drawable drawable){
        mCacheDrawable = drawable;
    }
}
