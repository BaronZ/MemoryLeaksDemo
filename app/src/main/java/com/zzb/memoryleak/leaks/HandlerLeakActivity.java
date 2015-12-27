package com.zzb.memoryleak.leaks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zzb.memoryleak.R;

public class HandlerLeakActivity extends AppCompatActivity {
    private static final String TAG = "HandlerLeakActivity";
    private InnerStaticHandler mInnerStaticHandler;
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
    }
    private void leak0(){


    }
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    private class InnerHandler extends Handler{

    }
    private class InnerStaticHandler extends Handler{
        private TextView tv;//what about not TextView but some fields without ref to context
        public InnerStaticHandler(TextView tv, Callback callback){
            super(callback);
            this.tv = tv;
        }
    }
    private class InnerStaticWeakHandler extends Handler{
        
    }
}
