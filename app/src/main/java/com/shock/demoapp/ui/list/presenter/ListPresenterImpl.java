package com.shock.demoapp.ui.list.presenter;

import com.shock.demoapp.ui.list.interactor.ListInteractor;
import com.shock.demoapp.ui.list.module.DataItem;
import com.shock.demoapp.ui.list.view.ListView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by shahid on 12/8/16.
 */
public class ListPresenterImpl implements ListPresenter{

    public ListInteractor listInteractor;
    public ListView listView;

    @Inject
    public ListPresenterImpl(ListInteractor listInteractor,ListView listView){
        this.listInteractor = listInteractor;
        this.listView = listView;
    }

    @Override
    public void getData() {
        listInteractor.getData(this);
    }

    @Override
    public void onSuccess(List<DataItem> dataItems) {
        listView.hideProgress();
        listView.onSucess(dataItems);
    }

    @Override
    public void onFailure(String errorMessage) {
        listView.onFailure(errorMessage);
    }
}
