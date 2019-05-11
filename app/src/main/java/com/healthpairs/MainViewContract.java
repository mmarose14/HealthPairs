package com.healthpairs;

import com.healthpairs.adapter.FoodListAdapter;
import com.healthpairs.models.FoodItem;
import com.healthpairs.models.FoodPair;

import java.util.List;

public interface MainViewContract {

    interface IMainView {

        void loadDefaultFoods(List<FoodItem> foods);

        void launchFoodPairsActivityWithFood(FoodItem foodItem);
    }

    interface IMainViewPresenter {

        void onResume();

        void setFoodListData(List<FoodItem> foods);

        FoodItem getSelectedFoodItem(int position);
    }
}
