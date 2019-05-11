package com.healthpairs;

import android.app.Application;

import com.healthpairs.data.FoodsLocalDataSource;

public class MainApplication extends Application {
    private static MainApplication INSTANCE = null;

    public static MainApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        FoodsLocalDataSource.getInstance().getAllFoodPairs();
    }
}
