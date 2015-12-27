package com.zzb.memoryleak.leaks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zzb.memoryleak.R;

public class SingletonLeakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton_leak);
        leak0();
    }
    private void leak0(){
        SingletonLeak.getInstance(this);
    }
}
