package com.shock.demoapp.ui.list.presenter;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.shock.demoapp.ui.list.module.DataItem;

import java.util.List;

/**
 * Created by shahid on 10/8/16.
 */
public interface OnCompletedListener{
    public void onSuccess(List<DataItem> dataItems);
    public void onFailure(String errorMessage);
}
