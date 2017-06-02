package com.yonder.exercise.shared.listeners;

import android.support.v4.view.ViewPager;

/**
 * Created by YusufMac on 01/06/17.
 */

public abstract class ViewPagerOnPageChangeListener implements ViewPager.OnPageChangeListener {
    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int i) {
        onPageChanged(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    public abstract void onPageChanged(int position);
}
