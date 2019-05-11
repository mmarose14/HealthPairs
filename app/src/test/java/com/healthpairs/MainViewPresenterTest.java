package com.healthpairs;

import com.healthpairs.data.FoodsLocalDataSource;
import com.healthpairs.data.LoadFoodsInteractor;
import com.healthpairs.models.FoodPair;
import com.healthpairs.search.SearchViewInteractor;
import com.healthpairs.util.TestFoodsUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainViewPresenterTest {

    MainViewPresenter mainViewPresenter;
    LoadFoodsInteractor loadFoodsInteractor;

    @Mock
    MainViewContract.IMainView mainView;

    @Mock
    FoodsLocalDataSource foodsLocalDataSource;

    List<FoodPair> foodPairs;


    @Before
    public void setupSearchFoodsPresenter() {
        MockitoAnnotations.initMocks(this);

        foodPairs = TestFoodsUtil.generateTestFoodsMapping();

        loadFoodsInteractor = spy(new LoadFoodsInteractor(foodsLocalDataSource));

        mainViewPresenter = new MainViewPresenter(mainView, loadFoodsInteractor);
    }

    @Test
    public void testLoadDefaultFoods() {
        when(foodsLocalDataSource.getAllFoodPairs()).thenReturn(foodPairs);

        mainViewPresenter.onResume();

        verify(loadFoodsInteractor).loadDefaultFoods(mainViewPresenter);
        verify(mainView).loadDefaultFoods(anyList());

    }


}
