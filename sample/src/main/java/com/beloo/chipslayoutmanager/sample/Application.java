package com.beloo.chipslayoutmanager.sample;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
//            if (BuildConfig.isReportCrashes) {
//                FirebaseCrash.report(e);
//            }
//        });
    }
}
