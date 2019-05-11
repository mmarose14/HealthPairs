package com.healthpairs;

import com.healthpairs.data.BaseLocalDataSource;
import com.healthpairs.models.FoodItem;
import com.healthpairs.models.FoodPair;
import com.healthpairs.search.ISearchViewInteractor;
import com.healthpairs.search.SearchViewInteractor;
import com.healthpairs.util.TestFoodsUtil;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.healthpairs.TestFoodsConstants.APPLE;
import static com.healthpairs.TestFoodsConstants.RASPBERRIES;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class SearchViewInteractorTest {

    private SearchViewInteractor searchViewInteractor;
    private List<FoodPair> foodPairs;

    @Mock
    BaseLocalDataSource foodsLocalDataSource;

    @Mock
    ISearchViewInteractor.OnSearchResultsListener listener;

    @Before
    public void setupSearchViewInteractor() {
        MockitoAnnotations.initMocks(this);

        foodPairs = TestFoodsUtil.generateTestFoodsMapping();

        searchViewInteractor = new SearchViewInteractor(foodsLocalDataSource);
    }

    @Test
    public void testSearchQuery() {
        when(foodsLocalDataSource.getAllFoodPairs()).thenReturn(foodPairs);

        List<FoodItem> matchingFoodItems = searchViewInteractor.searchForPairedFoodsFromQuery("apple", foodPairs);

        Assert.assertNotNull(matchingFoodItems);
        Assert.assertEquals(RASPBERRIES, matchingFoodItems.get(0).getName());

        matchingFoodItems = searchViewInteractor.searchForPairedFoodsFromQuery(APPLE, foodPairs);

        Assert.assertNotNull(matchingFoodItems);
        Assert.assertEquals(RASPBERRIES, matchingFoodItems.get(0).getName());

    }

    @Test
    public void testSearchQueryEmpty() {
        searchViewInteractor.onSearchText("", listener);
        searchViewInteractor.onSearchText(" ", listener);
        searchViewInteractor.onSearchText(null, listener);

        verifyNoMoreInteractions(listener);
    }
}
