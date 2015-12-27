package com.zzb.memoryleak.leaks;

import android.content.Context;

/**
 * 单例引用Context内存泄露
 * Created by ZZB on 2015/12/26.
 */
public class SingletonLeak {

    private static SingletonLeak sInstance;
    private Context mContext;
    //Context泄露
    public static SingletonLeak getInstance(Context context) {
        if(sInstance == null){
            sInstance = new SingletonLeak(context);
        }
        return sInstance;
    }
    private SingletonLeak(Context context) {
        mContext = context;
    }
}
