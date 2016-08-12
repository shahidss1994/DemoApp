package com.shock.demoapp.ui.login.presenter;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.shock.demoapp.ui.login.interactor.LoginInteractor;
import com.shock.demoapp.ui.login.view.LoginView;

import javax.inject.Inject;

/**
 * Created by shahid on 10/8/16.
 */
public class LoginPresenterImpl implements LoginPresenter{

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    @Inject
    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        loginView.showSuccess(loginResult);
    }

    @Override
    public void onError(FacebookException exception) {
        loginView.showError(exception);
    }

    @Override
    public void onCancel() {
        loginView.showCancel();
    }
}
