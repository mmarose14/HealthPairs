package com.healthpairs.models;

import com.google.common.base.Strings;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static com.healthpairs.TestFoodsConstants.APPLE;

public class FootItemTest {

    public static final String BANANA = "Banana";

    private FoodItem foodItemNoImage;
    private FoodItem foodItemWithImage;

    @Before
    public void setupFoodItem() {
        foodItemNoImage = new FoodItem(APPLE);
        foodItemWithImage = new FoodItem(BANANA, "test_image_url");

    }

    @Test
    public void testFoodItem() {
        Assert.assertNotNull(foodItemNoImage);
        Assert.assertEquals(APPLE, foodItemNoImage.getName());
    }

    @Test
    public void testEmptyImage() {
        Assert.assertTrue(Strings.isNullOrEmpty(foodItemNoImage.getImage()));
    }

    @Test
    public void testFoodWithImage() {
        Assert.assertFalse(Strings.isNullOrEmpty(foodItemWithImage.getImage()));

    }
}
