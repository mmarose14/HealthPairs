package com.healthpairs;

import com.healthpairs.data.FoodsLocalDataSource;
import com.healthpairs.data.ILoadFoodsInteractor;
import com.healthpairs.data.LoadFoodsInteractor;
import com.healthpairs.models.FoodItem;
import com.healthpairs.models.FoodPair;
import com.healthpairs.util.TestFoodsUtil;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.healthpairs.TestFoodsConstants.APPLE;
import static com.healthpairs.TestFoodsConstants.RASPBERRIES;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoadFoodsInteractorTest {

    private LoadFoodsInteractor loadFoodsInteractor;

    @Mock
    ILoadFoodsInteractor.OnFoodsRetrievedListener listener;

    @Mock
    FoodsLocalDataSource foodsLocalDataSource;

    List<FoodPair> foodPairs;

    @Before
    public void setupFoodsInteractor() {
        MockitoAnnotations.initMocks(this);
        foodPairs = TestFoodsUtil.generateTestFoodsMapping();
        loadFoodsInteractor = spy(new LoadFoodsInteractor(foodsLocalDataSource));
    }

    @Test
    public void generateFoodsJson() {
        TestFoodsUtil.generateAllFoodsMapping();
    }

    @Test
    public void testLoadDefaultFoods() {
        when(foodsLocalDataSource.getAllFoodPairs()).thenReturn(foodPairs);

        loadFoodsInteractor.loadDefaultFoods(listener);

        verify(listener).onFinishedGettingDefaultFoods(anyList());
    }

    @Test
    public void testListOfFoodPairsForGivenGood() {
        List<FoodPair> matchingFoodPairs = loadFoodsInteractor.getListOfFoodPairsForFood(APPLE, foodPairs);

        Assert.assertEquals(APPLE, matchingFoodPairs.get(0).getFoodOne().getName());
        Assert.assertEquals(RASPBERRIES, matchingFoodPairs.get(0).getFoodTwo().getName());
    }

    @Test
    public void testLoadFoodsForEmptyFoodName() {
        List<FoodPair> matchingFoodPairs;

        matchingFoodPairs = loadFoodsInteractor.getFoodPairsForGivenFood("", listener);
        Assert.assertNull(matchingFoodPairs);
        matchingFoodPairs = loadFoodsInteractor.getFoodPairsForGivenFood(null, listener);
        Assert.assertNull(matchingFoodPairs);

    }

    @Test
    public void testExtractFoodPairs() {
        List<FoodItem> extractedListOfFoods = loadFoodsInteractor.extractListOfFoods(APPLE, foodPairs);
        FoodItem foodItem = extractedListOfFoods.get(0);

        Assert.assertEquals(RASPBERRIES, foodItem.getName());
    }


}
