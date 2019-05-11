package com.healthpairs;

import com.healthpairs.data.BaseLocalDataSource;
import com.healthpairs.models.FoodPair;
import com.healthpairs.pairs.PairsViewContract;
import com.healthpairs.pairs.PairsViewPresenter;
import com.healthpairs.data.ILoadFoodsInteractor;
import com.healthpairs.data.LoadFoodsInteractor;
import com.healthpairs.util.TestFoodsUtil;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.healthpairs.TestFoodsConstants.APPLE;
import static com.healthpairs.TestFoodsConstants.RASPBERRIES;
import static org.mockito.Mockito.when;

public class PairsViewPresenterTest {

    private PairsViewPresenter pairsViewPresenter;
    LoadFoodsInteractor loadFoodsInteractor;

    @Mock
    PairsViewContract.IPairsView pairsView;

    @Mock
    BaseLocalDataSource foodsLocalDataSource;

    @Mock
    ILoadFoodsInteractor.OnFoodsRetrievedListener listener;

    List<FoodPair> foodPairs;

    @Before
    public void setupPairsViewPresenter() {
        MockitoAnnotations.initMocks(this);

        foodPairs = TestFoodsUtil.generateTestFoodsMapping();

        loadFoodsInteractor = new LoadFoodsInteractor(foodsLocalDataSource);

        pairsViewPresenter = new PairsViewPresenter(pairsView, loadFoodsInteractor);
    }

    @Test
    public void testGetAssociatedFoodPair() {
        when(foodsLocalDataSource.getAllFoodPairs()).thenReturn(foodPairs);
        pairsViewPresenter.loadFoodPairsForGivenFood(APPLE);

        FoodPair foodPair = pairsViewPresenter.getAssociatedFoodPair(0);
        Assert.assertEquals(APPLE, foodPair.getFoodOne().getName());
        Assert.assertEquals(RASPBERRIES, foodPair.getFoodTwo().getName());
    }


}
