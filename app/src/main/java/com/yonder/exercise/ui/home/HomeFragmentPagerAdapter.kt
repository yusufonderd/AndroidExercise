package com.yonder.exercise.ui.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.ArrayList
import java.util.Arrays


 class HomeFragmentPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private val mFragmentList = ArrayList<Fragment>()
    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }
    override fun getCount(): Int {
        return mFragmentList.size
    }
    fun addFragments(vararg fragments: Fragment) {
        mFragmentList.addAll(Arrays.asList(*fragments))
    }
}