package com.zzb.memoryleak;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zzb.memoryleak.leaks.AnonymousClassLeakActivity;
import com.zzb.memoryleak.leaks.HandlerLeakActivity;
import com.zzb.memoryleak.leaks.InnerClassLeakActivity;
import com.zzb.memoryleak.leaks.SingletonContextLeakActivity;
import com.zzb.memoryleak.leaks.StaticDrawableActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_handler:
                toActivity(HandlerLeakActivity.class);
                break;
            case R.id.btn_inner_class:
                toActivity(InnerClassLeakActivity.class);
                break;
            case R.id.btn_anonymous:
                toActivity(AnonymousClassLeakActivity.class);
                break;
            case R.id.btn_singleton_context:
                toActivity(SingletonContextLeakActivity.class);
                break;
            case R.id.btn_static_drawable:
                toActivity(StaticDrawableActivity.class);
                break;
        }
    }

    void toActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
