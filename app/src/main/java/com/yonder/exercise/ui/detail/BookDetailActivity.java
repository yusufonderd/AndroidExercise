package com.yonder.exercise.ui.detail;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.orhanobut.hawk.Hawk;
import com.yonder.exercise.R;
import com.yonder.exercise.db.BookModel;
import com.yonder.exercise.db.DBConstants;
import com.yonder.exercise.network.model.SingleBook;
import com.yonder.exercise.shared.utils.BooksUtils;
import com.yonder.exercise.shared.listeners.AppBarStateChangeListener;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookDetailActivity extends LifecycleActivity {


    String TAG = BookDetailActivity.class.getSimpleName();

    // @BindView(R.id.tvPageCount)
    //        TextView tvPageCount;


    @BindView(R.id.tvBookTitle)
    TextView tvBookTitle;

    BookDetailViewModel detailViewModel;

    @BindView(R.id.tvDescription)
    TextView tvDescription;

    @BindView(R.id.imgView)
    ImageView imgView;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbar;



    @BindView(R.id.centerCardView)
    CardView centerCardView;


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static final String BOOK_ID = "book_id";

    String bookId;

    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;

    BookModel bookModel;

    @BindView(R.id.favButton)
    ImageButton favButton;

    Bundle bundle;

    boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);
        detailViewModel = ViewModelProviders.of(this).get(BookDetailViewModel.class);
        bundle = getIntent().getExtras();
        bookId = bundle.getString(BOOK_ID);
        detailViewModel.loadBook(bookId);
        Hawk.init(BookDetailActivity.this).build();
        boolean ifKeyExists = Hawk.contains(bookId);
        if (ifKeyExists) {
            isFavorite = Hawk.get(bookId);
            setFabImageResource(isFavorite);
        } else {
            setFabImageResource(false);
        }
        detailViewModel.getBook().observe(this, singleBook -> {
            mCollapsingToolbar.setTitle(BooksUtils.getAuthor(singleBook));
            tvDescription.setText(singleBook.getVolumeInfo().getDescription());
            tvBookTitle.setText(singleBook.getVolumeInfo().getTitle());
            bookModel = BooksUtils.getBookModel(singleBook);
            bookModel.setBookFav(BooksUtils.getBookFab(bookModel));
            if (singleBook.getVolumeInfo().getImageLinks() != null) {
                Glide.with(BookDetailActivity.this) // could be an issue!
                        .load(singleBook.getVolumeInfo().getImageLinks().getLarge())
                        .into(glideTarget);
            }
        });
        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    centerCardView.setVisibility(View.VISIBLE);
                } else {
                    centerCardView.setVisibility(View.GONE);

                }
            }
        });

    }

    public void createPaletteAsync(Bitmap bitmap) {
        Palette.from(bitmap).generate(palette -> {
            Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
            if (vibrantSwatch != null) {
                mCollapsingToolbar.setContentScrimColor(vibrantSwatch.getRgb());
                mCollapsingToolbar.setCollapsedTitleTextColor(vibrantSwatch.getTitleTextColor());

            }
        });
    }


    @OnClick
    public void onClickFavoriteButton(View view) {
        if (bookModel != null) {

            String newFavValue = DBConstants.FAV;
            if (isFavorite) {
                newFavValue = DBConstants.UNFAV;
            }
            isFavorite = !isFavorite;
            bookModel.setBookFav(newFavValue);
            changeFavoriteState(isFavorite);
            detailViewModel.updateBook(bookModel);
        }
    }

    private SimpleTarget glideTarget = new SimpleTarget<Drawable>() {
        @Override
        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
            Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
            createPaletteAsync(bitmap);
            imgView.setImageDrawable(resource);
        }
    };


    private void setFabImageResource(boolean fav) {
        if (fav)
            favButton.setImageResource(R.drawable.ic_favorite_black_24dp);
        else
            favButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
    }

    private void changeFavoriteState(boolean fav) {
        setFabImageResource(fav);
        if (fav) {
            Snackbar.make(favButton, R.string.book_added_to_favorite, Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(favButton, R.string.book_removed_to_favorite, Snackbar.LENGTH_LONG).show();
        }
        Hawk.put(bookId, fav);
    }

}
