package com.shock.demoapp.di.module;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shock.demoapp.di.DemoApp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;


import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shahid on 10/8/16.
 */
@Module
public class NetworkModule {

    private final String BASE_URL = "http://testapp.rentsetgo.co";

    DemoApp demoApp;

    public NetworkModule(DemoApp demoApp) {
        this.demoApp = demoApp;
    }

    @Provides
    @Singleton
    @Named("RESTFUL")
    Cache provideCacheRestful(Application application) {
        int cacheSize = 15 * 1024 * 1024;   // 15 MB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    @Named("FRESCO")
    Cache provideCacheFresco(Application application) {
        int cacheSize = 15 * 1024 * 1024;   // 15 MB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.create();
    }

    @Provides
    @Singleton
    @Named("RESTFUL")
    OkHttpClient provideOkhttpClientRestful(@Named("RESTFUL") Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    @Named("FRESCO")
    OkHttpClient provideOkhttpClientFresco(@Named("FRESCO") Cache cache) {
        int timeout = 10; // default 10 seconds
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .connectTimeout(timeout, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        builder.cache(cache);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                // if we want to grab a specific cookie or something.. place here..
                return response;
            }
        });
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    ImagePipelineConfig initializeFresco(@Named("FRESCO") OkHttpClient client) {
        // Preparing config.
        return OkHttpImagePipelineConfigFactory
                .newBuilder(demoApp.getApplicationContext(), client)
                .build();


    }
}
