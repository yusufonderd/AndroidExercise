package com.yonder.exercise.ui.home
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.yonder.exercise.R
import com.yonder.exercise.shared.listeners.ViewPagerOnPageChangeListener
import com.yonder.exercise.ui.books.BooksFragment
import com.yonder.exercise.ui.favorites.FavoritesFragment
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    lateinit var fragmentPagerAdapter: HomeFragmentPagerAdapter
    lateinit var favoritesFragment: FavoritesFragment
    lateinit var booksFragment: BooksFragment
    var onPageChangeListener: ViewPagerOnPageChangeListener = object : ViewPagerOnPageChangeListener() {
        override fun onPageChanged(position: Int) {
            if (position == 0)
                navigation.selectedItemId = R.id.navigation_home
            else
                navigation.selectedItemId = R.id.navigation_favorite
        }
    }
    var mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewPager.setCurrentItem(0, true)
                return@OnNavigationItemSelectedListener true }
            R.id.navigation_favorite -> {
                viewPager.setCurrentItem(1, true)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ButterKnife.bind(this)
        fragmentPagerAdapter = HomeFragmentPagerAdapter(supportFragmentManager)
        favoritesFragment = FavoritesFragment.newInstance()
        booksFragment = BooksFragment.newInstance()
        fragmentPagerAdapter.addFragments(booksFragment, favoritesFragment)
        viewPager.adapter = fragmentPagerAdapter
        viewPager.addOnPageChangeListener(onPageChangeListener)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
