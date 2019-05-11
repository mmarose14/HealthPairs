package com.healthpairs.search;

import com.healthpairs.data.BaseLocalDataSource;
import com.healthpairs.models.FoodItem;
import com.healthpairs.models.FoodPair;
import com.healthpairs.util.StringUtility;

import java.util.ArrayList;
import java.util.List;

public class SearchViewInteractor implements ISearchViewInteractor {

    private BaseLocalDataSource localDataSource;

    public SearchViewInteractor(BaseLocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    @Override
    public void onSearchText(String query, OnSearchResultsListener listener) {
        if (StringUtility.isStringNotNullOrEmpty(query)) {
            List<FoodItem> matchingFoods = searchForPairedFoodsFromQuery(query, localDataSource.getAllFoodPairs());
            listener.onSearchResults(matchingFoods);
        }
    }

    public List<FoodItem> searchForPairedFoodsFromQuery(String foodName, List<FoodPair> allFoodPairs) {
        List<FoodItem> pairedFoods = new ArrayList<>();

        //There's probably a more efficient way of doing this
        //Find the given food in the list of FoodPairs and add its pairings
        for (FoodPair foodPair : allFoodPairs) {
            if (foodName.equalsIgnoreCase(foodPair.getFoodOne().getName())) {
                pairedFoods.add(foodPair.getFoodTwo());
            }

            if (foodName.equalsIgnoreCase(foodPair.getFoodTwo().getName())) {
                pairedFoods.add(foodPair.getFoodOne());
            }
        }
        return pairedFoods;
    }
}
