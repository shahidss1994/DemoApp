package com.shock.demoapp.ui.list.interactor;

import com.shock.demoapp.ui.list.module.DataItem;
import com.shock.demoapp.ui.list.module.DataService;
import com.shock.demoapp.ui.list.presenter.OnCompletedListener;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

/**
 * Created by shahid on 12/8/16.
 */
public class ListInteractorImpl implements ListInteractor{

    @Inject
    DataService dataService;

    @Inject
    public ListInteractorImpl(){

    }

    @Override
    public void getData(final OnCompletedListener onCompletedListener) {
        dataService.getData().subscribe(new Observer<List<DataItem>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                onCompletedListener.onFailure(e.getMessage());
            }

            @Override
            public void onNext(List<DataItem> dataItems) {
                onCompletedListener.onSuccess(dataItems);
            }
        });
    }
}
