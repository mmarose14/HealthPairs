package com.healthpairs.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.healthpairs.R;
import com.healthpairs.component.ImageLoader;
import com.healthpairs.models.FoodItem;
import com.healthpairs.models.FoodPair;
import com.healthpairs.util.StringUtility;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> {

    private final FoodItemListener mListener;
    private final Context mContext;

    private List<FoodItem> mFoodsList = new ArrayList<>();


    public FoodListAdapter(Context context, FoodItemListener listener) {
        this.mContext = context;
        this.mListener = listener;

    }

    public void reloadFoodListData(List<FoodItem> foods) {
        mFoodsList = foods;
        notifyDataSetChanged();
    }

    @Override
    public FoodListAdapter.FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.food_item, parent, false);
        return new FoodViewHolder(view);

    }

    @Override
    public void onBindViewHolder(FoodListAdapter.FoodViewHolder holder, int position) {
        FoodItem foodItem = mFoodsList.get(position);

        holder.nameText.setText(foodItem.getName());

        if (StringUtility.isStringNotNullOrEmpty(foodItem.getImage())) {
            ImageLoader.loadImage(mContext, foodItem.getImage(), holder.imageView);
        } else {
            holder.imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.empty_food_image));
        }

    }

    @Override
    public int getItemCount() {
        return mFoodsList.size();
    }

    protected class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView nameText;
        private final ImageView imageView;

        public FoodViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            nameText = (TextView)itemView.findViewById(R.id.food_name);
            imageView = (ImageView)itemView.findViewById(R.id.food_image);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            mListener.onFoodItemClick(position);
        }
    }

    public interface FoodItemListener {
        void onFoodItemClick(int position);
    }
}
