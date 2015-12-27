package com.zzb.memoryleak.leaks;

import java.util.ArrayList;
import java.util.List;

/**
 * 匿名内部类泄露
 * Created by ZZB on 2015/12/27.
 */
public class SingletonAnonymousLeak {
    private static SingletonAnonymousLeak sInstance;
    private List<Listener> mListeners = new ArrayList<>();
    private Listener mListener;

    public void release() {
        mListeners = new ArrayList<>();
        mListener = null;
    }

    public interface Listener{
        void doSomething();
    }

    private SingletonAnonymousLeak(){}
    public static SingletonAnonymousLeak getInstance(){
        if(sInstance == null){
            sInstance = new SingletonAnonymousLeak();
        }
        return sInstance;
    }
    public void addListener(Listener listener){
        mListeners.add(listener);
    }
    public void removeListener(Listener listener){
        mListeners.remove(listener);
    }
    public void setListener(Listener listener){
        mListener = listener;
    }
}
