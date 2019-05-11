package com.healthpairs.data;

import com.healthpairs.models.FoodPair;

import java.util.List;

public interface BaseLocalDataSource {

    List<FoodPair> getAllFoodPairs();
}
