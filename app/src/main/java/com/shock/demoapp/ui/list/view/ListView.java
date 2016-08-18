package com.shock.demoapp.ui.list.view;

import com.shock.demoapp.ui.list.module.DataItem;

import java.util.List;

/**
 * Created by shahid on 12/8/16.
 */
public interface ListView {

    public void showProgress();
    public void hideProgress();
    public void onSuccess(ListItemRecyclerViewAdapter dataItems);
    public void onFailure(String msg);

}
