package com.zzb.memoryleak.leaks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zzb.memoryleak.R;

public class SingletonContextLeakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton_leak);
        leak0();
    }
    private void leak0(){
        SingletonContextLeak.getInstance(this);
    }
    //解决方法，用ApplicationContext代替Activity
    private void noLeak(){
        SingletonContextLeak.getInstance(this.getApplicationContext());
    }
}
