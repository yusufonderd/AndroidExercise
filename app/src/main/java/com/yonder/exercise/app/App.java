package com.yonder.exercise.app;

import android.app.Application;

import com.yonder.exercise.di.AppComponent;
import com.yonder.exercise.di.AppModule;
import com.yonder.exercise.di.DaggerAppComponent;

/**
 * Created by YusufMac on 29/05/17.
 */

public class App extends Application {

    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }

    protected AppComponent initDagger(App application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}

