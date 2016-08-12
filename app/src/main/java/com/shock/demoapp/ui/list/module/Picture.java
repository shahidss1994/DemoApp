package com.shock.demoapp.ui.list.module;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shahid on 12/8/16.
 */
public class Picture implements Serializable{

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
