package com.healthpairs.pairs;

import com.healthpairs.models.FoodItem;
import com.healthpairs.models.FoodPair;

import java.util.List;

public interface PairsViewContract {

    interface IPairsView {

        void showFoodPairs(List<FoodItem> foods);
    }

    interface IPairsViewPresenter {

        void loadFoodPairsForGivenFood(String foodName);

        FoodPair getAssociatedFoodPair(int position);
    }
}
