package com.shock.demoapp.di.module;

import com.shock.demoapp.ui.list.module.DataService;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by shahid on 12/8/16.
 */
@Module
public class ServiceModel {

    @Provides
    DataService provideDataService(Retrofit retrofit){
        return retrofit.create(DataService.class);
    }

}
