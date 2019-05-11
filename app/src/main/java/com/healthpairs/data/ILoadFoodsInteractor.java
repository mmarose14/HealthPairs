package com.healthpairs.data;

import com.healthpairs.models.FoodItem;
import com.healthpairs.models.FoodPair;

import java.util.List;

public interface ILoadFoodsInteractor {

    List<FoodPair> getFoodPairsForGivenFood(String foodName, OnFoodsRetrievedListener listener);

    interface OnFoodsRetrievedListener {
        void onFinishedGettingDefaultFoods(List<FoodItem> foods);

        void onFinishedGettingFoods(String foodName, List<FoodPair> matchingFoodPairs);
    }

    void loadFoods(OnFoodsRetrievedListener listener);
    void loadDefaultFoods(OnFoodsRetrievedListener listener);
}
