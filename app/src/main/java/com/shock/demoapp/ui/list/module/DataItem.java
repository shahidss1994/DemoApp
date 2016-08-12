package com.shock.demoapp.ui.list.module;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shahid on 12/8/16.
 */
public class DataItem implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("dlv_available")
    private int dlvAvailable;

    @SerializedName("score")
    private String score;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("location")
    private String location;

    @SerializedName("min_rental_period")
    private int minRentalPeriod;

    @SerializedName("status")
    private String status;

    @SerializedName("tnc")
    private String tnc;

    @SerializedName("category_id")
    private int categoryId;

    @SerializedName("category")
    private String category;

    @SerializedName("per_day_rent")
    private int perDayRent;

    @SerializedName("per_week_rent")
    private int perWeekRent;

    @SerializedName("per_month_rent")
    private int perMonthRent;

    @SerializedName("url")
    private String url;

    @SerializedName("pictures")
    private List<Picture> pictures;

    @SerializedName("blockedDates")
    private List<String> blockedDates;

    @SerializedName("partiallyBlockedDates")
    private List<String> partiallyBlockedDates;

    @SerializedName("user_image_url")
    private String userImageUrl;

    @SerializedName("product_locality")
    private String productLocality;

    @SerializedName("address")
    private Address address;

    @SerializedName("avail_offer")
    private boolean availOffer;

    @SerializedName("offer_price")
    private int offerPrice;

    @SerializedName("offer_percentage")
    private int offerPercentage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDlvAvailable() {
        return dlvAvailable;
    }

    public void setDlvAvailable(int dlvAvailable) {
        this.dlvAvailable = dlvAvailable;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMinRentalPeriod() {
        return minRentalPeriod;
    }

    public void setMinRentalPeriod(int minRentalPeriod) {
        this.minRentalPeriod = minRentalPeriod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTnc() {
        return tnc;
    }

    public void setTnc(String tnc) {
        this.tnc = tnc;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPerDayRent() {
        return perDayRent;
    }

    public void setPerDayRent(int perDayRent) {
        this.perDayRent = perDayRent;
    }

    public int getPerWeekRent() {
        return perWeekRent;
    }

    public void setPerWeekRent(int perWeekRent) {
        this.perWeekRent = perWeekRent;
    }

    public int getPerMonthRent() {
        return perMonthRent;
    }

    public void setPerMonthRent(int perMonthRent) {
        this.perMonthRent = perMonthRent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<String> getBlockedDates() {
        return blockedDates;
    }

    public void setBlockedDates(List<String> blockedDates) {
        this.blockedDates = blockedDates;
    }

    public List<String> getPartiallyBlockedDates() {
        return partiallyBlockedDates;
    }

    public void setPartiallyBlockedDates(List<String> partiallyBlockedDates) {
        this.partiallyBlockedDates = partiallyBlockedDates;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getProductLocality() {
        return productLocality;
    }

    public void setProductLocality(String productLocality) {
        this.productLocality = productLocality;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isAvailOffer() {
        return availOffer;
    }

    public void setAvailOffer(boolean availOffer) {
        this.availOffer = availOffer;
    }

    public int getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(int offerPrice) {
        this.offerPrice = offerPrice;
    }

    public int getOfferPercentage() {
        return offerPercentage;
    }

    public void setOfferPercentage(int offerPercentage) {
        this.offerPercentage = offerPercentage;
    }
}
