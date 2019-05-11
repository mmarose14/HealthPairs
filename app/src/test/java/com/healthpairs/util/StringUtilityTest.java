package com.healthpairs.util;

import junit.framework.Assert;

import org.junit.Test;

public class StringUtilityTest {

    @Test
    public void testIsStringNullOrEmpty() {
        Assert.assertTrue(StringUtility.isStringNullOrEmpty(null));
        Assert.assertTrue(StringUtility.isStringNullOrEmpty(""));
        Assert.assertTrue(StringUtility.isStringNullOrEmpty(" "));
        Assert.assertTrue(StringUtility.isStringNullOrEmpty("  "));
    }

    @Test
    public void testIsStringNotNullOrEmptyReverseTest() {
        Assert.assertFalse(StringUtility.isStringNullOrEmpty("Test"));
    }

    @Test
    public void testIsStringNotNullOrEmpty() {
        Assert.assertTrue(StringUtility.isStringNotNullOrEmpty("Test"));
    }
}
