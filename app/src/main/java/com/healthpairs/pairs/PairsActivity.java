package com.healthpairs.pairs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.healthpairs.R;
import com.healthpairs.adapter.FoodListAdapter;
import com.healthpairs.component.ImageLoader;
import com.healthpairs.data.FoodsLocalDataSource;
import com.healthpairs.data.LoadFoodsInteractor;
import com.healthpairs.models.FoodItem;
import com.healthpairs.models.FoodPair;
import com.healthpairs.pairs.details.PairDetailsActivity;
import com.healthpairs.util.StringUtility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PairsActivity extends AppCompatActivity implements PairsViewContract.IPairsView, FoodListAdapter.FoodItemListener {

    public static final String EXTRA_FOOD_ITEM = "extra_food_item";

    private PairsViewPresenter pairsViewPresenter;
    private FoodListAdapter mFoodPairsAdapter;

    @BindView(R.id.pairs_list)
    RecyclerView pairsListView;

    @BindView(R.id.food_name)
    TextView foodNameText;

    @BindView(R.id.food_image)
    ImageView foodImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pairs);
        ButterKnife.bind(this);

        pairsViewPresenter = new PairsViewPresenter(this, new LoadFoodsInteractor(FoodsLocalDataSource.getInstance()));

        initFoodPairsView();

        if (savedInstanceState != null) {

        } else {
            if (getIntent().hasExtra(EXTRA_FOOD_ITEM)) {
                FoodItem foodItemFromExtra = getIntent().getParcelableExtra(EXTRA_FOOD_ITEM);
                loadFoodItemDetailsIntoView(foodItemFromExtra);
                loadFoodPairs(foodItemFromExtra);
            }
        }

    }

    private void loadFoodItemDetailsIntoView(FoodItem foodItemFromExtra) {
        foodNameText.setText(foodItemFromExtra.getName());

        if (StringUtility.isStringNotNullOrEmpty(foodItemFromExtra.getImage())) {
            ImageLoader.loadImage(this, foodItemFromExtra.getImage(), foodImage);
        } else {
            foodImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.empty_food_image));
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private void initFoodPairsView() {
        mFoodPairsAdapter = new FoodListAdapter(this, this);

        pairsListView.setLayoutManager(new LinearLayoutManager(this));
        pairsListView.setAdapter(mFoodPairsAdapter);
    }

    private void loadFoodPairs(FoodItem foodItem) {
        pairsViewPresenter.loadFoodPairsForGivenFood(foodItem.getName());
    }

    @Override
    public void showFoodPairs(List<FoodItem> foods) {
        mFoodPairsAdapter.reloadFoodListData(foods);
    }

    @Override
    public void onFoodItemClick(int position) {
        FoodPair foodPair = pairsViewPresenter.getAssociatedFoodPair(position);

        Intent intent = new Intent(this, PairDetailsActivity.class);
        intent.putExtra(PairDetailsActivity.EXTRA_FOOD_PAIR, foodPair);
        startActivity(intent);
    }
}
