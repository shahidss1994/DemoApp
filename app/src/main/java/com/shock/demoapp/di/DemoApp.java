package com.shock.demoapp.di;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.shock.demoapp.di.component.AppComponent;
import com.shock.demoapp.di.component.DaggerAppComponent;
import com.shock.demoapp.di.module.AppModule;
import com.shock.demoapp.di.module.NetworkModule;

import javax.inject.Inject;

/**
 * Created by shahid on 10/8/16.
 */
public class DemoApp extends Application {

    private static AppComponent appComponent;
    private static DemoApp demoApp;

    @Inject
    ImagePipelineConfig config;

    @Override
    public void onCreate() {
        super.onCreate();
        demoApp = this;
        intializeFaceBookSdk();
        initAppComponent();
        initFresco();
    }

    public void intializeFaceBookSdk() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }

    public void initAppComponent() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(demoApp)).networkModule(new NetworkModule(demoApp)).build();
    }

    public static DemoApp get() {
        return demoApp;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void initFresco() {
        config = get().getAppComponent().createImagePipelineConfig();
        get().getAppComponent().injectImagePipelineConfig(config);
        Fresco.initialize(demoApp.getApplicationContext(), config);
    }

}
