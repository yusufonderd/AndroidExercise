package com.yonder.exercise.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.orhanobut.hawk.Hawk
import com.yonder.exercise.R
import com.yonder.exercise.db.BookModel
import com.yonder.exercise.db.DBConstants
import com.yonder.exercise.shared.utils.BooksUtils
import com.yonder.exercise.shared.listeners.AppBarStateChangeListener

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.content_book_detail.*

class BookDetailActivity : AppCompatActivity() {

    lateinit var detailViewModel: BookDetailViewModel
    internal var bookId: String? = null
    var bookModel: BookModel? = null
    lateinit var bundle: Bundle
    var isFavorite = false

    private val glideTarget = object : SimpleTarget<Drawable>() {
        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>) {
            val bitmap = (resource as BitmapDrawable).bitmap
            createPaletteAsync(bitmap)
            imgView!!.setImageDrawable(resource)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        ButterKnife.bind(this)
        detailViewModel = ViewModelProviders.of(this).get(BookDetailViewModel::class.java)
        detailViewModel.book.observe(this, Observer { singleBook ->
            if (singleBook != null) {
                toolbar_layout.title = BooksUtils.getAuthor(singleBook)
                tvDescription.setText(singleBook.getVolumeInfo().getDescription())
                tvBookTitle.setText(singleBook.getVolumeInfo().getTitle())
                bookModel = BooksUtils.getBookModel(singleBook)
                bookModel!!.bookFav = BooksUtils.getBookFab(bookModel!!)
                if (singleBook.getVolumeInfo().getImageLinks() != null) {
                    Glide.with(this@BookDetailActivity)
                            .load(singleBook.getVolumeInfo().getImageLinks().getLarge())
                            .into<SimpleTarget<Drawable>>(glideTarget)
                }
            }
        })
        initVariables()
        initViews()

        app_bar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: AppBarStateChangeListener.State) {
                if (state === AppBarStateChangeListener.State.EXPANDED) {
                    centerCardView.visibility = View.VISIBLE
                } else {
                    centerCardView.visibility = View.GONE
                }
            }
        })

    }

    fun initVariables() {
        bundle = intent.extras
        bookId = bundle.getString(BOOK_ID)
        detailViewModel.loadBook(bookId!!)
        Hawk.init(this@BookDetailActivity).build()
    }

    fun initViews() {
        val ifBookIdStored = Hawk.contains(bookId)
        if (ifBookIdStored) {
            isFavorite = Hawk.get(bookId)
            setFabImageResource(isFavorite)
        } else {
            setFabImageResource(false)
        }
    }

    fun createPaletteAsync(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            val vibrantSwatch = palette.vibrantSwatch
            if (vibrantSwatch != null) {
                toolbar_layout.setContentScrimColor(vibrantSwatch.rgb)
                toolbar_layout.setCollapsedTitleTextColor(vibrantSwatch.titleTextColor)
            }
        }
    }


    @OnClick
    fun onClickFavoriteButton(view: View) {
        if (bookModel != null) {
            var newFavValue = DBConstants.FAV
            if (isFavorite) newFavValue = DBConstants.UNFAV
            isFavorite = !isFavorite
            bookModel!!.bookFav = newFavValue
            changeFavoriteState(isFavorite)
            detailViewModel.updateBook(bookModel!!)
        }
    }


    private fun setFabImageResource(fav: Boolean) {
        if (fav)
            favButton.setImageResource(R.drawable.ic_favorite_black_24dp)
        else
            favButton.setImageResource(R.drawable.ic_favorite_border_black_24dp)
    }

    private fun changeFavoriteState(fav: Boolean) {
        setFabImageResource(fav)
        if (fav) {
            Snackbar.make(favButton, R.string.book_added_to_favorite, Snackbar.LENGTH_LONG).show()
        } else {
            Snackbar.make(favButton, R.string.book_removed_to_favorite, Snackbar.LENGTH_LONG).show()
        }
        Hawk.put(bookId, fav)
    }

    companion object {
        val BOOK_ID = "book_id"
    }

}
