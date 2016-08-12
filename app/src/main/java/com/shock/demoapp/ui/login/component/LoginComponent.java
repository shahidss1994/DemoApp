package com.shock.demoapp.ui.login.component;

import com.shock.demoapp.ui.login.module.LoginModule;
import com.shock.demoapp.ui.login.view.LoginActivityFragment;

import dagger.Subcomponent;

/**
 * Created by shahid on 11/8/16.
 */
@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent {

    void inject(LoginActivityFragment loginActivityFragment);

}
