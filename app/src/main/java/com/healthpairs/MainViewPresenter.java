package com.healthpairs;

import android.support.annotation.NonNull;

import com.healthpairs.adapter.FoodListAdapter;
import com.healthpairs.data.FoodsLocalDataSource;
import com.healthpairs.models.FoodItem;
import com.healthpairs.models.FoodPair;
import com.healthpairs.data.ILoadFoodsInteractor;
import com.healthpairs.data.LoadFoodsInteractor;
import com.healthpairs.search.SearchViewInteractor;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainViewPresenter implements MainViewContract.IMainViewPresenter, ILoadFoodsInteractor.OnFoodsRetrievedListener {

    private MainViewContract.IMainView mainView;
    private LoadFoodsInteractor loadFoodsInteractor;
    private List<FoodItem> foodsListData;
    private FoodsLocalDataSource foodsLocalDataSource;

    public MainViewPresenter(@NonNull MainViewContract.IMainView mainView,
                             @NonNull LoadFoodsInteractor loadFoodsInteractor) {
        this.mainView = checkNotNull(mainView, "mainView cannot be null!");
        this.loadFoodsInteractor = checkNotNull(loadFoodsInteractor, "loadFoodsInteractor cannot be null!");
    }

    @Override
    public void onResume() {
        loadFoodsInteractor.loadDefaultFoods(this);
    }

/*    @Override
    public FoodListAdapter.FoodItemListener getFoodItemClickListener() {
        return new FoodListAdapter.FoodItemListener() {
            @Override
            public void onFoodItemClick(int position) {
                FoodItem foodItem = foodsListData.get(position);
                mainView.launchFoodPairsActivityWithFood(foodItem);
            }
        };
    }*/

    @Override
    public void setFoodListData(List<FoodItem> foods) {
        foodsListData = foods;
    }

    @Override
    public FoodItem getSelectedFoodItem(int position) {
        return foodsListData.get(position);
    }

    @Override
    public void onFinishedGettingDefaultFoods(List<FoodItem> foods) {
        if (mainView != null) {
            foodsListData = foods;
            mainView.loadDefaultFoods(foods);
        }
    }

    @Override
    public void onFinishedGettingFoods(String foodName, List<FoodPair> matchingFoodPairs) {

    }
}
