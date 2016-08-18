package com.shock.demoapp.ui.list.interactor;

import com.shock.demoapp.ui.list.module.DataItem;
import com.shock.demoapp.ui.list.module.DataService;
import com.shock.demoapp.ui.list.presenter.OnCompletedListener;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shahid on 12/8/16.
 */
public class ListInteractorImpl implements ListInteractor {

    @Inject
    DataService dataService;

    @Inject
    public ListInteractorImpl() {

    }

    @Override
    public void getData(final OnCompletedListener onCompletedListener) {
        dataService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<DataItem>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        try {
                            onCompletedListener.onFailure("Unable to get data.");
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<DataItem> dataItems) {
                        onCompletedListener.onSuccess(dataItems);
                    }
                });
    }

    @Override
    public void sortByPrice(List<DataItem> dataItems, final boolean asc) {
        Collections.sort(dataItems, new Comparator<DataItem>() {
            @Override
            public int compare(DataItem dataItem, DataItem t1) {
                int price1 = dataItem.getOfferPrice() > 0 ? dataItem.getOfferPrice() : dataItem.getPerDayRent();
                int price2 = t1.getOfferPrice() > 0 ? t1.getOfferPrice() : t1.getPerDayRent();
                if (price1 < price2)
                    return asc == true ? -1 : 1;
                else if (price1 > price2)
                    return asc == true ? 1 : -1;
                else
                    return 0;
            }
        });
    }

    @Override
    public void sortByStar(List<DataItem> dataItems, final boolean asc) {
        Collections.sort(dataItems, new Comparator<DataItem>() {
            @Override
            public int compare(DataItem dataItem, DataItem t1) {
                float score1 = Float.parseFloat(dataItem.getScore());
                float score2 = Float.parseFloat(t1.getScore());
                if (score1 < score2)
                    return asc == true ? -1 : 1;
                else if (score1 > score2)
                    return asc == true ? 1 : -1;
                else
                    return 0;
            }
        });
    }

    @Override
    public void sortByDiscount(List<DataItem> dataItems, final boolean asc) {
        Collections.sort(dataItems, new Comparator<DataItem>() {
            @Override
            public int compare(DataItem dataItem, DataItem t1) {
                if (dataItem.getOfferPercentage() < t1.getOfferPercentage())
                    return asc == true ? -1 : 1;
                else if (dataItem.getOfferPercentage() > t1.getOfferPercentage())
                    return asc == true ? 1 : -1;
                else
                    return 0;
            }
        });
    }
}
