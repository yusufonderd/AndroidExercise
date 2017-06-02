package com.yonder.exercise.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.yonder.exercise.R;
import com.yonder.exercise.db.AppDatabase;
import com.yonder.exercise.shared.listeners.ViewPagerOnPageChangeListener;
import com.yonder.exercise.ui.books.BooksFragment;
import com.yonder.exercise.ui.favorites.FavoritesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    HomeFragmentPagerAdapter fragmentPagerAdapter;


    FavoritesFragment favoritesFragment;
    BooksFragment booksFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        fragmentPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        favoritesFragment = FavoritesFragment.newInstance();
        booksFragment = BooksFragment.newInstance();
        fragmentPagerAdapter.addFragments(booksFragment, favoritesFragment);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    ViewPagerOnPageChangeListener onPageChangeListener = new ViewPagerOnPageChangeListener() {
        @Override
        public void onPageChanged(int position) {
            if (position == 0)
                bottomNavigationView.setSelectedItemId(R.id.navigation_home);
            else
                bottomNavigationView.setSelectedItemId(R.id.navigation_favorite);
        }
    };
    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0, true);
                    return true;
                case R.id.navigation_favorite:
                    viewPager.setCurrentItem(1, true);
                    return true;
            }
            return false;
        }
    };
}
