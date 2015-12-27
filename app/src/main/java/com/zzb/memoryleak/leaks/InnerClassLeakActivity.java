package com.zzb.memoryleak.leaks;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zzb.memoryleak.R;

public class InnerClassLeakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_class_leak);
        leak0();
    }
    private void leak0(){
        new LongRunTask().execute();
    }
    private class LongRunTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void[] params) {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
