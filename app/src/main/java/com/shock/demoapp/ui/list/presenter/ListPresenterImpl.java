package com.shock.demoapp.ui.list.presenter;

import android.content.Context;

import com.shock.demoapp.di.DemoApp;
import com.shock.demoapp.ui.list.interactor.ListInteractor;
import com.shock.demoapp.ui.list.module.DataItem;
import com.shock.demoapp.ui.list.view.ListItemRecyclerViewAdapter;
import com.shock.demoapp.ui.list.view.ListView;
import com.shock.demoapp.ui.list.view.OnListFragmentInteractionListener;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by shahid on 12/8/16.
 */
public class ListPresenterImpl implements ListPresenter {

    public ListInteractor listInteractor;
    public ListView listView;
    public Context context;
    public OnListFragmentInteractionListener listListener;
    public List<DataItem> dataItems;
    public ListItemRecyclerViewAdapter adapter;

    @Inject
    public ListPresenterImpl(ListInteractor listInteractor, ListView listView, OnListFragmentInteractionListener listListener) {
        this.listListener = listListener;
        this.listInteractor = listInteractor;
        this.listView = listView;
        this.context = DemoApp.get().getApplicationContext();
    }

    @Override
    public void getData() {
        listView.showProgress();
        listInteractor.getData(this);
    }

    @Override
    public void onSuccess(List<DataItem> dataItems) {
        this.dataItems = dataItems;
        adapter = new ListItemRecyclerViewAdapter(context, this.dataItems, listListener);
        listView.hideProgress();
        listView.onSuccess(adapter);
    }

    @Override
    public void onFailure(String errorMessage) {
        listView.hideProgress();
        listView.onFailure(errorMessage);
    }

    @Override
    public void sortByPrice(boolean asc) {
        listInteractor.sortByPrice(dataItems, asc);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void sortByStar(boolean asc) {
        listInteractor.sortByStar(dataItems, asc);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void sortByDiscount(boolean asc) {
        listInteractor.sortByDiscount(dataItems, asc);
        adapter.notifyDataSetChanged();
    }
}
