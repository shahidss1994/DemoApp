package com.shock.demoapp.ui.list.module;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by shahid on 12/8/16.
 */
public interface DataService {

    @GET("/task.txt")
    Observable<List<DataItem>> getData();

}
