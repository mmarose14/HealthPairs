package com.healthpairs.models;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodItem implements Parcelable {

    private String name;
    private String image;

    public FoodItem(String name) {
        this.name = name;
        this.image = "";
    }

    public FoodItem(String name, String image) {
        this.name = name;
        this.image = image;
    }

    protected FoodItem(Parcel in) {
        name = in.readString();
        image = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FoodItem> CREATOR = new Parcelable.Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };
}
