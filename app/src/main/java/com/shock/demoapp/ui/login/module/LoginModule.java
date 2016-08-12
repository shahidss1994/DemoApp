package com.shock.demoapp.ui.login.module;

import com.shock.demoapp.ui.login.interactor.LoginInteractor;
import com.shock.demoapp.ui.login.interactor.LoginInteractorImpl;
import com.shock.demoapp.ui.login.presenter.LoginPresenter;
import com.shock.demoapp.ui.login.presenter.LoginPresenterImpl;
import com.shock.demoapp.ui.login.view.LoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shahid on 10/8/16.
 */
@Module
public class LoginModule {

    public final LoginView loginView;

    public LoginModule(LoginView loginView) {
        this.loginView = loginView;
    }

    @Provides
    LoginView provideLoginView() {
        return loginView;
    }

    @Provides
    LoginInteractor provideLoginInteractor(LoginInteractorImpl loginInteractorImpl){
        return loginInteractorImpl;
    }

    @Provides
    LoginPresenter provideLoginPresenter(LoginPresenterImpl loginPresenterImpl){
        return loginPresenterImpl;
    }

}
