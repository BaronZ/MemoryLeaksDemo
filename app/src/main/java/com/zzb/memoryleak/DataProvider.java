package com.zzb.memoryleak;

/**
 * Created by ZZB on 2015/12/28.
 */
public class DataProvider {
    public static int sCounter;
    public static int getImageResId(){
        int index = (sCounter++) % 5;
        int resId = R.drawable.large1;
        switch (index) {
            case 0:
                resId = R.drawable.img3;
                break;
            case 1:
                resId = R.drawable.large2;
                break;
            case 2:
                resId = R.drawable.large3;
                break;
            case 3:
                resId = R.drawable.large4;
                break;
        }
        return resId;
    }
}
