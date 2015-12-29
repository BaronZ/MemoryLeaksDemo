package com.zzb.memoryleak.leaks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzb.memoryleak.DataProvider;
import com.zzb.memoryleak.R;

import java.lang.ref.WeakReference;

public class HandlerLeakActivity extends AppCompatActivity {
    private static final String TAG = "HandlerLeakActivity";
    private InnerStaticHandler mInnerStaticHandler;
    private String mStringField;
    public static final int DELAY_IN_MILLIS = 60000;
    private InnerHandler mInnerHandler;
    private View mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_leak);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.setImageResource(DataProvider.getImageResId());
        mBtn = findViewById(R.id.btn_release);
        mBtn.setOnClickListener(v ->{
                mInnerHandler.removeMessages(0);
                mInnerHandler = null;
        });
        leak0();
        //.removeCallbacks
    }



    private void leak0() {
        Message msg = new Message();
        msg.what = 0;
        msg.obj = "InnerHandler msg";
        mInnerHandler = new InnerHandler();
        mInnerHandler.sendMessageDelayed(msg, DELAY_IN_MILLIS);

    }

    private void leak1() {
        Message msg = new Message();
        msg.what = 1;
        msg.obj = "InnerHandler msg";
        InnerStaticHandler handler = new InnerStaticHandler(mBtn);
    }

    private class InnerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i(TAG, msg.obj.toString());
        }
    }

    private static class InnerStaticHandler extends Handler {
        private View view;//what about not TextView but some fields without ref to context
        private String stringField;

        public InnerStaticHandler(View view) {
            this.view = view;
        }

        public InnerStaticHandler(String stringField) {
            this.stringField = stringField;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }

    private class InnerStaticWeakHandler extends Handler {
        private WeakReference<TextView> tvRef;

        public InnerStaticWeakHandler(TextView tv) {
            tvRef = new WeakReference<TextView>(tv);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
//
    }
    //call in onStop
    private void releaseInnerHandler(){
        mInnerHandler.removeMessages(0);
    }
}
