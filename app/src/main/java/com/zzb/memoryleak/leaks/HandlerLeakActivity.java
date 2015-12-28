package com.zzb.memoryleak.leaks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zzb.memoryleak.R;

import java.lang.ref.WeakReference;

public class HandlerLeakActivity extends AppCompatActivity {
    private static final String TAG = "HandlerLeakActivity";
    private InnerStaticHandler mInnerStaticHandler;
    private String mStringField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_leak);
        Message msg = new Message();
        msg.obj = "mHandler";
//        mHandler.sendMessageDelayed(msg, 5000);
        Message msg1 = new Message();
        msg1.obj = "sHandler";
//        sHandler.sendMessage(msg1);
        //.removeCallbacks
    }
    private void leak0(){


    }


    private class InnerHandler extends Handler{
        private TextView tv;
        public InnerHandler(TextView tv){
            this.tv = tv;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }
    private class InnerStaticHandler extends Handler{
        private TextView tv;//what about not TextView but some fields without ref to context
        private String stringField;
        public InnerStaticHandler(TextView tv){
            this.tv = tv;
        }
        public InnerStaticHandler(String stringField){
            this.stringField = stringField;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }
    private class InnerStaticWeakHandler extends Handler{
        private WeakReference<TextView> tvRef;
        public InnerStaticWeakHandler(TextView tv){
            tvRef = new WeakReference<TextView>(tv);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

}
