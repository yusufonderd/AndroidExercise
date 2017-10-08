package com.yonder.exercise.shared.listeners

import android.support.v4.view.ViewPager

/**
 * Created by YusufMac on 01/06/17.
 */

abstract class ViewPagerOnPageChangeListener : ViewPager.OnPageChangeListener {
    override fun onPageScrolled(i: Int, v: Float, i1: Int) {}

    override fun onPageSelected(i: Int) {
        onPageChanged(i)
    }

    override fun onPageScrollStateChanged(i: Int) {}

    abstract fun onPageChanged(position: Int)
}
