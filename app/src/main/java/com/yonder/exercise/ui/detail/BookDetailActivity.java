package com.yonder.exercise.ui.detail;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.yonder.exercise.R;
import com.yonder.exercise.network.model.SingleBook;
import com.yonder.exercise.ui.books.Utils;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetailActivity extends LifecycleActivity {


    BookDetailViewModel detailViewModel;

    @BindView(R.id.tvDescription)
    TextView tvDescription;

    @BindView(R.id.imgView)
    ImageView imgView;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static final String BOOK_ID = "book_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);
        detailViewModel = ViewModelProviders.of(this).get(BookDetailViewModel.class);
        Bundle bundle = getIntent().getExtras();
        detailViewModel.loadBook(bundle.getString(BOOK_ID));
        detailViewModel.getBook().observe(this, new Observer<SingleBook>() {
            @Override
            public void onChanged(@Nullable SingleBook singleBook) {
                collapsingToolbarLayout.setTitle(Utils.getAuthor(singleBook));
                tvDescription.setText(singleBook.getVolumeInfo().getDescription());
                if (singleBook.getVolumeInfo().getImageLinks() != null)
                    Glide.with(BookDetailActivity.this) // could be an issue!
                            .load(singleBook.getVolumeInfo().getImageLinks().getLarge())
                            .into(glideTarget);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.book_added_to_favorite, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void createPaletteAsync(Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(@NotNull Palette palette) {
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                if (vibrantSwatch != null) {
                    collapsingToolbarLayout.setContentScrimColor(vibrantSwatch.getRgb());
                    collapsingToolbarLayout.setCollapsedTitleTextColor(vibrantSwatch.getTitleTextColor());
                }
            }
        });
    }

    private SimpleTarget glideTarget = new SimpleTarget<Drawable>() {
        @Override
        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
            Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
            createPaletteAsync(bitmap);
            imgView.setImageDrawable(resource);
        }
    };


}
