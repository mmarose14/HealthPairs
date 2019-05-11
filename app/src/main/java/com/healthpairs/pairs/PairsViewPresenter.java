package com.healthpairs.pairs;

import android.support.annotation.NonNull;

import com.healthpairs.models.FoodItem;
import com.healthpairs.models.FoodPair;
import com.healthpairs.data.ILoadFoodsInteractor;
import com.healthpairs.data.LoadFoodsInteractor;

import java.util.ArrayList;
import java.util.List;

public class PairsViewPresenter implements PairsViewContract.IPairsViewPresenter, ILoadFoodsInteractor.OnFoodsRetrievedListener {

    private LoadFoodsInteractor loadFoodsInteractor;
    private PairsViewContract.IPairsView pairsView;
    private List<FoodPair> associatedFoodPairs;

    public PairsViewPresenter(@NonNull PairsViewContract.IPairsView pairsView,
                              @NonNull LoadFoodsInteractor loadFoodsInteractor) {
        this.pairsView = pairsView;
        this.loadFoodsInteractor = loadFoodsInteractor;
    }


    @Override
    public void loadFoodPairsForGivenFood(String foodName) {
        onFinishedGettingFoods(foodName, loadFoodsInteractor.getFoodPairsForGivenFood(foodName, this));
    }

    @Override
    public FoodPair getAssociatedFoodPair(int position) {
        return this.associatedFoodPairs.get(position);
    }

    @Override
    public void onFinishedGettingDefaultFoods(List<FoodItem> foods) {

    }

    @Override
    public void onFinishedGettingFoods(String foodName, List<FoodPair> matchingFoodPairs) {
        List<FoodItem> foodItems = extractFoodsFromPairs(foodName, matchingFoodPairs);

        if (pairsView != null) {
            pairsView.showFoodPairs(foodItems);
        }

        this.associatedFoodPairs = matchingFoodPairs;
    }

    private List<FoodItem> extractFoodsFromPairs(String foodName, List<FoodPair> matchingFoodPairs) {
        List<FoodItem> pairedFoods = new ArrayList<>();

        //There's probably a more efficient way of doing this
        //Find the given food in the list of FoodPairs and add its pairings
        for (FoodPair foodPair : matchingFoodPairs) {
            if (foodName.equals(foodPair.getFoodOne().getName())) {
                pairedFoods.add(foodPair.getFoodTwo());
            }

            if (foodName.equals(foodPair.getFoodTwo().getName())) {
                pairedFoods.add(foodPair.getFoodOne());
            }
        }
        return pairedFoods;
    }
}
