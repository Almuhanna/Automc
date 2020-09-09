
package com.creative.automc.utils;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

    public Context mContext;

    public Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

    }

}


