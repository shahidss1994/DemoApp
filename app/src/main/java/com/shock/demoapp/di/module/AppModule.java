package com.shock.demoapp.di.module;

import android.app.Application;
import android.content.res.Resources;

import com.shock.demoapp.di.DemoApp;
import com.shock.demoapp.util.ToolBox;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shahid on 10/8/16.
 */
@Module
public class AppModule {

    DemoApp demoApp;

    public AppModule(DemoApp demoApp){
        this.demoApp = demoApp;
    }

    @Provides
    @Singleton
    protected Application provideApplication(){
        return demoApp;
    }

    @Provides
    @Singleton
    protected Resources provideResources(){
        return demoApp.getResources();
    }

    @Provides
    @Singleton
    protected ToolBox provideToolBox(){
        return new ToolBox(demoApp.getApplicationContext());
    }

}
