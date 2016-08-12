package com.shock.demoapp.di.component;

import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.shock.demoapp.di.module.AppModule;
import com.shock.demoapp.di.module.NetworkModule;
import com.shock.demoapp.di.module.ServiceModel;
import com.shock.demoapp.ui.list.component.ListComponent;
import com.shock.demoapp.ui.list.module.ListModule;
import com.shock.demoapp.ui.login.component.LoginComponent;
import com.shock.demoapp.ui.login.module.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by shahid on 10/8/16.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, ServiceModel.class})
public interface AppComponent {

    ImagePipelineConfig createImagePipelineConfig();

    void injectImagePipelineConfig(ImagePipelineConfig imagePipelineConfig);

    LoginComponent plus(LoginModule loginModule);

    ListComponent plus(ListModule listModule);

}
