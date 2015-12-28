package com.zzb.memoryleak.leaks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.zzb.memoryleak.DataProvider;
import com.zzb.memoryleak.R;

/**
 * 匿名内部类泄露，如果没有释放，退出该activity,点击gc内存不会下降，点击realse之后，内存下降
 * created at 2015/12/27 21:52
 */
public class AnonymousClassLeakActivity extends AppCompatActivity implements SingletonAnonymousLeak.Listener {
    private SingletonAnonymousLeak.Listener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anonymous_class_leak);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.setImageResource(DataProvider.getImageResId());
        findViewById(R.id.btn_release).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingletonAnonymousLeak.getInstance().release();
            }
        });
        leak0();
    }



    private void leak0() {
        SingletonAnonymousLeak.getInstance().addListener(new SingletonAnonymousLeak.Listener() {
            @Override
            public void doSomething() {
            }
        });
    }

    private void leak1() {
        SingletonAnonymousLeak.getInstance().setListener(this);
    }

   

    @Override
    protected void onStop() {
        super.onStop();
        //解决方法：在Activity结束的时候，释放引用
        // SingletonAnonymousLeak.getInstance().release();
    }


    @Override
    public void doSomething() {

    }
}
