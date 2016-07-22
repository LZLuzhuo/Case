package me.luzhuo.homepagea.app;

import android.app.Application;

import me.luzhuo.homepagea.utils.AndroidUniversalImageLoaderImpl;

/**
 * =================================================
 * <p/>
 * Author: 卢卓
 * <p/>
 * Version: 1.0
 * <p/>
 * Creation Date: 2016/7/22 11:05
 * <p/>
 * Description:
 * <p/>
 * Revision History:
 * <p/>
 * Copyright:
 * <p/>
 * =================================================
 **/
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        new AndroidUniversalImageLoaderImpl().init(this);
    }
}
