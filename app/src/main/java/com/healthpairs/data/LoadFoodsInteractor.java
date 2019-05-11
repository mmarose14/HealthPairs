package com.healthpairs.data;

import android.support.annotation.NonNull;

import com.healthpairs.data.BaseLocalDataSource;
import com.healthpairs.data.ILoadFoodsInteractor;
import com.healthpairs.models.FoodItem;
import com.healthpairs.models.FoodPair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadFoodsInteractor implements ILoadFoodsInteractor {

    private BaseLocalDataSource foodsLocalDataSource;

    public LoadFoodsInteractor(BaseLocalDataSource foodsLocalDataSource) {
        this.foodsLocalDataSource = foodsLocalDataSource;
    }

    @Override
    public List<FoodPair> getFoodPairsForGivenFood(String foodName, OnFoodsRetrievedListener listener) {
        if (foodName != null && !foodName.equals("")) {
            List<FoodPair> matchingFoodPairs = getListOfFoodPairsForFood(foodName, foodsLocalDataSource.getAllFoodPairs());
            //pairedFoods = extractListOfFoods(foodName, foodsLocalDataSource.getAllFoodPairs());
            //listener.onFinishedGettingFoods(foodName, matchingFoodPairs);
            return matchingFoodPairs;
        } else {
            return null;
        }

    }

    public List<FoodPair> getListOfFoodPairsForFood(String foodName, List<FoodPair> allFoodPairs) {
        List<FoodPair> matchingFoodPairs = new ArrayList<>();

        for (FoodPair foodPair : allFoodPairs) {
            String foodNameOne = foodPair.getFoodOne().getName();
            String foodNameTwo = foodPair.getFoodTwo().getName();
            if (foodName.equals(foodNameOne) || foodName.equals(foodNameTwo)) {
                matchingFoodPairs.add(foodPair);
            }
        }

        return matchingFoodPairs;
    }

    @NonNull
    public List<FoodItem> extractListOfFoods(String foodName, List<FoodPair> allFoodPairs) {
        List<FoodItem> pairedFoods = new ArrayList<>();

        //There's probably a more efficient way of doing this
        //Find the given food in the list of FoodPairs and add its pairings
        for (FoodPair foodPair : allFoodPairs) {
            if (foodName.equals(foodPair.getFoodOne().getName())) {
                pairedFoods.add(foodPair.getFoodTwo());
            }

            if (foodName.equals(foodPair.getFoodTwo().getName())) {
                pairedFoods.add(foodPair.getFoodOne());
            }
        }
        return pairedFoods;
    }

    @Override
    public void loadFoods(OnFoodsRetrievedListener listener) {

    }

    @Override
    public void loadDefaultFoods(OnFoodsRetrievedListener listener) {
        listener.onFinishedGettingDefaultFoods(createDefaultFoodsList());
    }

    private List<FoodItem> createDefaultFoodsList() {
        List<FoodPair> allFoodPairs = foodsLocalDataSource.getAllFoodPairs();

        List<FoodItem> defaultFoodItems = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            defaultFoodItems.add(allFoodPairs.get(i).getFoodOne());
        }

        return defaultFoodItems;

        /*
        String appleImage = "https://firebasestorage.googleapis.com/v0/b/bites-1c0d2.appspot.com/o/Apple-icon.png?alt=media&token=a8c79a5e-1787-4416-8e32-2b395edc56a5";
        return Arrays.asList(
                new FoodItem("Apple",
                        appleImage),
                new FoodItem("Green Tea",
                        "https://firebasestorage.googleapis.com/v0/b/bites-1c0d2.appspot.com/o/green-tea-in-a-glass-teacup.jpg?alt=media&token=092c480b-6d1a-468b-829e-2ebe2437f55b")
        );
        */
    }
}
