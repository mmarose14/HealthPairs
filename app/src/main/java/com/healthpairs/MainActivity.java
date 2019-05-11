package com.healthpairs;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.healthpairs.adapter.FoodListAdapter;
import com.healthpairs.data.FoodsLocalDataSource;
import com.healthpairs.models.FoodItem;
import com.healthpairs.pairs.PairsActivity;
import com.healthpairs.data.LoadFoodsInteractor;
import com.healthpairs.search.ISearchViewInteractor;
import com.healthpairs.search.SearchViewInteractor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainViewContract.IMainView,
        ISearchViewInteractor.OnSearchResultsListener, FoodListAdapter.FoodItemListener {

    @BindView(R.id.foods_list_view)
    RecyclerView foodsListView;

    @BindView(R.id.search_view)
    SearchView searchView;

    private MainViewPresenter mainViewPresenter;
    private FoodListAdapter mFoodsItemAdapter;
    private SearchViewInteractor searchViewInteractor;
    private ISearchViewInteractor.OnSearchResultsListener searchResultsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        searchViewInteractor = new SearchViewInteractor(FoodsLocalDataSource.getInstance());

        mainViewPresenter = new MainViewPresenter(this,
                new LoadFoodsInteractor(FoodsLocalDataSource.getInstance()));

        initFoodsListView();

        searchResultsListener = this;

        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.onActionViewExpanded();
        searchView.setIconified(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewInteractor.onSearchText(query, searchResultsListener);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initFoodsListView() {
        mFoodsItemAdapter = new FoodListAdapter(this, this);

        foodsListView.setLayoutManager(new LinearLayoutManager(this));
        foodsListView.setAdapter(mFoodsItemAdapter);
    }

    private void loadFoodsListView(List<FoodItem> foods) {
        mFoodsItemAdapter.reloadFoodListData(foods);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainViewPresenter.onResume();
    }

    @Override
    public void loadDefaultFoods(List<FoodItem> foods) {
        loadFoodsListView(foods);
    }

    @Override
    public void launchFoodPairsActivityWithFood(FoodItem foodItem) {
        Intent intent = new Intent(this, PairsActivity.class);
        intent.putExtra(PairsActivity.EXTRA_FOOD_ITEM,foodItem);
        startActivity(intent);
    }

    @Override
    public void onSearchResults(List<FoodItem> foods) {
        mainViewPresenter.setFoodListData(foods);
        mFoodsItemAdapter.reloadFoodListData(foods);
    }

    @Override
    public void onFoodItemClick(int position) {
        launchFoodPairsActivityWithFood(mainViewPresenter.getSelectedFoodItem(position));
    }
}
