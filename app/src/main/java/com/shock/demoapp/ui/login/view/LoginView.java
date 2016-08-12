package com.shock.demoapp.ui.login.view;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by shahid on 10/8/16.
 */
public interface LoginView {
    public void showSuccess(LoginResult loginResult);
    public void showCancel();
    public void showError(FacebookException e);
}
