package com.healthpairs.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.healthpairs.models.FoodItem;
import com.healthpairs.models.FoodPair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestFoodsUtil {


    public static List<FoodPair> generateTestFoodsMapping() {
        List<FoodPair> foodPairs = new ArrayList<>();

        foodPairs.add(addPair("Apple",
                null,
                "Raspberries",
                null,
                "A study in the Journal of Nutrition determined that the antioxidant ellagic " +
                        "acid (found in raspberries, pomegranates, walnuts, and cranberries) enhanced " +
                        "the ability of quercetin (an antioxidant found in apples, grapes, onions, and " +
                        "buckwheat) to kill off cancerous cells.",
                "www.rd.com"));

        foodPairs.add(addPair("Red Wine",
                null,
                "Apple",
                null,
                "Apples contain an anti-inflammatory flavonoid called quercetin, while red wine contains " +
                        "the flavonoid catechin. Together, they work to prevent blood clots and improve " +
                        "cardiovascular functioning.",
                "www.thedailybeast.com"));

        foodPairs.add(addPair("Green Tea",
                null,
                "Lemon",
                null,
                "According to a separate Purdue University report, adding a splash of citrus juice " +
                        "from a lemon, lime, or grapefruit to green tea reduces the breakdown of its " +
                        "catechins in our digestive system, making them even more readily absorbed by the body.",
                "www.rd.com"));

        Gson gson = new Gson();
        String json = gson.toJson(foodPairs).toString();

        return foodPairs;
    }

    public static void generateAllFoodsMapping() {
        List<FoodPair> foodPairs = new ArrayList<>();

        foodPairs.add(addPair("Apple",
                "https://firebasestorage.googleapis.com/v0/b/bites-1c0d2.appspot.com/o/Apple-icon.png?alt=media&token=a8c79a5e-1787-4416-8e32-2b395edc56a5",
                "Raspberries",
                "https://firebasestorage.googleapis.com/v0/b/bites-1c0d2.appspot.com/o/raspberry.png?alt=media&token=59286508-4b4c-43dd-8964-ad04927759f3",
                "A study in the Journal of Nutrition determined that the antioxidant ellagic " +
                        "acid (found in raspberries, pomegranates, walnuts, and cranberries) enhanced " +
                        "the ability of quercetin (an antioxidant found in apples, grapes, onions, and " +
                        "buckwheat) to kill off cancerous cells.",
                "www.rd.com"));

        foodPairs.add(addPair("Red Wine",
                null,
                "Apple",
                "https://firebasestorage.googleapis.com/v0/b/bites-1c0d2.appspot.com/o/Apple-icon.png?alt=media&token=a8c79a5e-1787-4416-8e32-2b395edc56a5",
                "Apples contain an anti-inflammatory flavonoid called quercetin, while red wine contains " +
                        "the flavonoid catechin. Together, they work to prevent blood clots and improve " +
                        "cardiovascular functioning.",
                "www.thedailybeast.com"));

        foodPairs.add(addPair("Green Tea",
                "https://firebasestorage.googleapis.com/v0/b/bites-1c0d2.appspot.com/o/green-tea-in-a-glass-teacup.jpg?alt=media&token=092c480b-6d1a-468b-829e-2ebe2437f55b",
                "Lemon",
                null,
                "According to a separate Purdue University report, adding a splash of citrus juice " +
                        "from a lemon, lime, or grapefruit to green tea reduces the breakdown of its " +
                        "catechins in our digestive system, making them even more readily absorbed by the body.",
                "www.rd.com"));

        foodPairs.add(addPair("Tomato",
                null,
                "Avocado",
                null,
                "If you’re a Mexican food lover, combining tomatoes and avocado may be a no-brainer. " +
                        "The good news is that the healthy fat in avocados is believed to help the body " +
                        "absorb more lycopene, the antioxidant compound that gives red, orange and yellow " +
                        "produce its hue. Research from Ohio State University found that when avocado was " +
                        "added to salsa, people absorbed more than four times the amount of lycopene than from " +
                        "salsa without avocado. What makes this pair a winning combo? Increased consumption of " +
                        "lycopene plays a role in the cancer prevention, inflammatory diseases, and age-related illnesses such as cataracts. ",
                "www.dailyburn.com"));



        Gson gson = new Gson();
        String json = gson.toJson(foodPairs).toString();
        System.out.print(json);
    }

    private static FoodPair addPair(String foodOne, String foodOneImage,
                             String foodTwo, String foodTwoImage, String description, String source) {

        FoodItem foodItemOne = new FoodItem(foodOne, foodOneImage);
        FoodItem foodItemTwo = new FoodItem(foodTwo, foodTwoImage);

        FoodPair foodPair = new FoodPair();
        foodPair.setFoodOne(foodItemOne);
        foodPair.setFoodTwo(foodItemTwo);
        foodPair.setDescription(
                description);
        foodPair.setSource(source);

        return foodPair;
    }

    public static List<FoodItem> createDefaultFoodsList() {
        return Arrays.asList(
                new FoodItem("Wine"),
                new FoodItem("Apple",
                        "https://firebasestorage.googleapis.com/v0/b/bites-1c0d2.appspot.com/o/Apple-icon.png?alt=media&token=a8c79a5e-1787-4416-8e32-2b395edc56a5"),
                new FoodItem("Green Tea",
                        "https://firebasestorage.googleapis.com/v0/b/bites-1c0d2.appspot.com/o/green-tea-in-a-glass-teacup.jpg?alt=media&token=092c480b-6d1a-468b-829e-2ebe2437f55b")
        );
    }
}
