package com.healthpairs.models;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodPair implements Parcelable{

    private FoodItem foodOne;
    private FoodItem foodTwo;
    private String description;
    private String source;

    public FoodPair() {}

    public FoodItem getFoodOne() {
        return foodOne;
    }

    public void setFoodOne(FoodItem foodOne) {
        this.foodOne = foodOne;
    }

    public FoodItem getFoodTwo() {
        return foodTwo;
    }

    public void setFoodTwo(FoodItem foodTwo) {
        this.foodTwo = foodTwo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(foodOne, flags);
        dest.writeParcelable(foodTwo, flags);
        dest.writeString(description);
        dest.writeString(source);
    }

    protected FoodPair(Parcel in) {
        foodOne = in.readParcelable(FoodItem.class.getClassLoader());
        foodTwo = in.readParcelable(FoodItem.class.getClassLoader());
        description = in.readString();
        source = in.readString();
    }

    public static final Creator<FoodPair> CREATOR = new Creator<FoodPair>() {
        @Override
        public FoodPair createFromParcel(Parcel in) {
            return new FoodPair(in);
        }

        @Override
        public FoodPair[] newArray(int size) {
            return new FoodPair[size];
        }
    };
}
