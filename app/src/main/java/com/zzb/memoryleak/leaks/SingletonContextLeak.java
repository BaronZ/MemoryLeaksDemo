package com.zzb.memoryleak.leaks;

import android.content.Context;

/**
 * 单例引用Context内存泄露
 * Created by ZZB on 2015/12/26.
 */
public class SingletonContextLeak {

    private static SingletonContextLeak sInstance;
    private Context mContext;
    //Context泄露
    public static SingletonContextLeak getInstance(Context context) {
        if(sInstance == null){
            sInstance = new SingletonContextLeak(context);
        }
        return sInstance;
    }
    private SingletonContextLeak(Context context) {
        mContext = context;
    }
}
