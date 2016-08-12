package com.shock.demoapp.ui.login.view;

import android.os.Bundle;

import com.shock.demoapp.R;
import com.shock.demoapp.ui.AppBaseActivity;

import butterknife.ButterKnife;

public class LoginActivity extends AppBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setToolbar(getResources().getString(R.string.app_name));
    }
}
