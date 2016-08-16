package com.zzb.memoryleak.leaks;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.zzb.memoryleak.DataProvider;
import com.zzb.memoryleak.R;

/**
 *  When a Drawable is attached to a view, the view is set as a callback on the drawable. In the code snippet above,
 *  this means the drawable has a reference to the TextView which itself has a reference to the activity (the Context)
 *  which in turns has references to pretty much anything (depending on your code.)
 */
public class StaticDrawableActivity extends AppCompatActivity {
    private static Drawable sDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_drawable);
        ImageView iv = (ImageView) findViewById(R.id.iv);

        sDrawable = getResources().getDrawable(DataProvider.getImageResId());
        iv.setImageDrawable(sDrawable);
        
    }
}
