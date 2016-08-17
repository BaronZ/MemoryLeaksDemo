package com.zzb.memoryleak.leaks;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zzb.memoryleak.R;

public class SingletonContextLeakActivity extends AppCompatActivity implements View.OnClickListener{
    private Context mContext;
    private TextView mTv;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton_leak);
        mTv = (TextView) findViewById(R.id.tv);
        mContext = this;
    }
    //引用了Activity，导致内存泄露
    private void leak0(){
        SingletonContextLeak.getInstance(mContext);
    }
    /**
     *  When a Drawable is attached to a view, the view is set as a callback on the drawable. In the code snippet above,
     *  this means the drawable has a reference to the TextView which itself has a reference to the activity (the Context)
     *  which in turns has references to pretty much anything (depending on your code.)
     */
    private void leak1() {//update: 这里的drawable的callback已经变成weak reference,不会再泄露了activity,只会泄露drawable本身
        Drawable drawable = getResources().getDrawable(R.drawable.img1);
        mTv.setBackgroundDrawable(drawable);
        Singleton.INSTANCE.cache(drawable);
    }
    //解决方法，用ApplicationContext代替Activity
    private void noLeak(){
        SingletonContextLeak.getInstance(mContext.getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_leak0:
                SingletonContextLeak.reset();
                leak0();
                break;
            case R.id.btn_leak1:
                SingletonContextLeak.reset();
                leak1();
                break;
            case R.id.btn_no_leak:
                SingletonContextLeak.reset();
                noLeak();
                break;
        }
    }


}
