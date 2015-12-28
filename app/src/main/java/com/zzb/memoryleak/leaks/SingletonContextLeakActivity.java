package com.zzb.memoryleak.leaks;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zzb.memoryleak.R;

public class SingletonContextLeakActivity extends AppCompatActivity {
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton_leak);
        mContext = this;
        leak0();
    }
    //引用了Activity，导致内存泄露
    private void leak0(){
        SingletonContextLeak.getInstance(mContext);
    }
    //解决方法，用ApplicationContext代替Activity
    private void noLeak(){
        SingletonContextLeak.getInstance(mContext.getApplicationContext());
    }
}
