package com.healthpairs.pairs.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.healthpairs.R;
import com.healthpairs.component.ImageLoader;
import com.healthpairs.models.FoodItem;
import com.healthpairs.models.FoodPair;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PairDetailsActivity extends AppCompatActivity implements PairDetailsContract.IPairDetailsView {

    public static final String EXTRA_FOOD_PAIR = "extra_food_pair";

    @BindView(R.id.food_image_one)
    ImageView foodImageOne;

    @BindView(R.id.food_image_two)
    ImageView foodImageTwo;

    @BindView(R.id.pair_description)
    TextView pairDescriptionText;

    @BindView(R.id.source_text)
    TextView sourceText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_details);
        ButterKnife.bind(this);

        if(savedInstanceState != null) {

        } else {
            if (getIntent().hasExtra(EXTRA_FOOD_PAIR)) {
                FoodPair foodPairFromExtra = getIntent().getParcelableExtra(EXTRA_FOOD_PAIR);
                loadFoodPairDetailsIntoViews(foodPairFromExtra);
            }
        }

    }

    private void loadFoodPairDetailsIntoViews(FoodPair foodPairFromExtra) {
        loadImageFromFoodItem(foodPairFromExtra.getFoodOne(), foodImageOne);
        loadImageFromFoodItem(foodPairFromExtra.getFoodTwo(), foodImageTwo);

        pairDescriptionText.setText(foodPairFromExtra.getDescription());
        sourceText.setText(foodPairFromExtra.getSource());
    }

    private void loadImageFromFoodItem(FoodItem foodItem, ImageView imageView) {
        if (!Strings.isNullOrEmpty(foodItem.getImage())) {
            ImageLoader.loadImage(this, foodItem.getImage(),imageView);
        }

    }
}
