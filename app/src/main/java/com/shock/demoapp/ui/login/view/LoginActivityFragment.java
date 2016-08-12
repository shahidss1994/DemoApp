package com.shock.demoapp.ui.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.shock.demoapp.R;
import com.shock.demoapp.di.DemoApp;
import com.shock.demoapp.ui.AppBaseFragment;
import com.shock.demoapp.ui.list.view.ListActivity;
import com.shock.demoapp.ui.login.module.LoginModule;
import com.shock.demoapp.ui.login.presenter.LoginPresenter;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends AppBaseFragment implements LoginView {

    private final String TAG = LoginActivityFragment.class.getSimpleName();

    @BindView(R.id.btn_facebook_login)
    LoginButton loginButton;

    @Inject
    LoginPresenter loginPresenter;

    private CallbackManager callbackManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        DemoApp.get().getAppComponent().plus(new LoginModule(this)).inject(this);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, loginPresenter);
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        return view;
    }

    @Override
    public void showSuccess(LoginResult loginResult) {
        Log.d(TAG, loginResult.getAccessToken().getToken());
        Intent intent = new Intent(getActivity(), ListActivity.class);
        startActivity(intent, false);
    }

    @Override
    public void showCancel() {
        Log.d(TAG, "Cancel By User");
    }

    @Override
    public void showError(FacebookException e) {
        e.printStackTrace();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
