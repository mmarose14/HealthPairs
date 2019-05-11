package com.healthpairs.data;

import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.healthpairs.MainApplication;
import com.healthpairs.models.FoodPair;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FoodsLocalDataSource implements BaseLocalDataSource {

    private static FoodsLocalDataSource INSTANCE;

    private List<FoodPair> foodPairs;

    private FoodsLocalDataSource() {
    }

    public static FoodsLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FoodsLocalDataSource();
        }

        return INSTANCE;
    }

    private List<FoodPair> getFoodPairs() {
        if (foodPairs != null) {
            return foodPairs;
        } else {
            String json = getDataFromFile();

            Gson gson = new Gson();
            foodPairs = gson.fromJson(json, new TypeToken<List<FoodPair>>(){}.getType());

            return foodPairs;
        }
    }

    public String getDataFromFile() {

        String json = null;
        try {
            InputStream is = MainApplication.getInstance().getAssets().open("foods.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    @Override
    public List<FoodPair> getAllFoodPairs() {
        return getFoodPairs();
    }
}
