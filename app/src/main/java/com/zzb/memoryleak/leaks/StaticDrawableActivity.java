package com.zzb.memoryleak.leaks;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.zzb.memoryleak.R;

/**
 *  When a Drawable is attached to a view, the view is set as a callback on the drawable. In the code snippet above,
 *  this means the drawable has a reference to the TextView which itself has a reference to the activity (the Context)
 *  which in turns has references to pretty much anything (depending on your code.)
 */
//update: 这里的drawable的callback已经变成weak reference,不会再泄露了activity,只会泄露drawable本身
public class StaticDrawableActivity extends AppCompatActivity {
    private static Drawable sDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_drawable);
        ImageView iv = (ImageView) findViewById(R.id.iv);

        sDrawable = getResources().getDrawable(R.drawable.large1);
        iv.setImageDrawable(sDrawable);

        findViewById(R.id.btn_release).setOnClickListener(v -> sDrawable = null);
    }
}
