package com.mysource.myview;

import android.app.Application;

import com.mysource.myview.mockup.MockUpManager;

/**
 * Created by Tung.Hoang on 05-12-15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MockUpManager.getInstance().init(this);
    }
}
