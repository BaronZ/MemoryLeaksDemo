package com.zzb.memoryleak.leaks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.zzb.memoryleak.DataProvider;
import com.zzb.memoryleak.R;

import java.lang.ref.WeakReference;

public class HandlerLeakActivity extends AppCompatActivity {
    private static final String TAG = "HandlerLeakActivity";
    private String mStringField = "someText";
    public static final int DELAY_IN_MILLIS = 60000;
    private InnerHandler mInnerHandler;
    private InnerStaticHandler mInnerStaticHandler;
    private InnerStaticWeakHandler mInnerStaticWeakHandler;
    private View mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_leak);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.setImageResource(DataProvider.getImageResId());
        mBtn = findViewById(R.id.btn_release);
        leak3();

    }



    private void leak0() {
        Message msg = new Message();
        msg.what = 0;
        msg.obj = "InnerHandler msg";
        mInnerHandler = new InnerHandler();
        mInnerHandler.sendMessageDelayed(msg, DELAY_IN_MILLIS);

    }
    //static no leak
    private void leak1() {
        Message msg = new Message();
        msg.what = 1;
        msg.obj = "InnerStaticHandler msg";
        mInnerStaticHandler = new InnerStaticHandler();
        mInnerStaticHandler.sendMessageDelayed(msg, DELAY_IN_MILLIS);
    }

    private void leak2(){
        Message msg = new Message();
        msg.what = 1;
        msg.obj = "InnerStaticHandler msg";
        mInnerStaticHandler = new InnerStaticHandler(mBtn);
//        mInnerStaticHandler = new InnerStaticHandler(mStringField);//fine without ref to activity
        mInnerStaticHandler.sendMessageDelayed(msg, DELAY_IN_MILLIS);
    }
    //no leak
    private void leak3(){
        Message msg = new Message();
        msg.what = 1;
        msg.obj = "InnerStaticWeakHandler msg";
        mInnerStaticWeakHandler = new InnerStaticWeakHandler(mBtn);
        mInnerStaticWeakHandler.sendMessageDelayed(msg, DELAY_IN_MILLIS);
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
        public InnerStaticHandler(){}
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

    private static class InnerStaticWeakHandler extends Handler {
        private WeakReference<View> vRef;

        public InnerStaticWeakHandler(View view) {
            vRef = new WeakReference<View>(view);
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
    private void solutionForInnerHandler(){
        mInnerHandler.removeMessages(0);
        //.removeCallbacks
    }
    private void solutionForInnerStaticHandler(){
        //use InnerStaticWeakHandler
    }
}
