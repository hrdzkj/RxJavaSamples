// (c)2016 Flipboard Inc, All Rights Reserved.

package com.rengwuxian.rxjavasamples;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class App extends Application {
    private static App INSTANCE;
    private RefWatcher refWatcher;

    public static App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        refWatcher = LeakCanary.install(this);
    }

    public  RefWatcher getRefWatcher() {
        return refWatcher;
    }
}

