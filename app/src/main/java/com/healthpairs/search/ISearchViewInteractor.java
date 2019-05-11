package com.healthpairs.search;

import com.healthpairs.models.FoodItem;

import java.util.List;

public interface ISearchViewInteractor {

    void onSearchText(String query, OnSearchResultsListener listener);

    interface OnSearchResultsListener {
        void onSearchResults(List<FoodItem> foods);
    }
}
