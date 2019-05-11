package com.healthpairs.component;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;

import com.healthpairs.util.StringUtility;
import com.squareup.picasso.Picasso;

public class ImageLoader {

    public static void loadImage(Context context, String url, ImageView imageView) {
        if (StringUtility.isStringNotNullOrEmpty(url)) {
            Picasso.with(context).load(url).into(imageView);
        } else {
            Log.e("ImageLoader", "Image Url for Picasso is empty!!!");
        }

    }
}
