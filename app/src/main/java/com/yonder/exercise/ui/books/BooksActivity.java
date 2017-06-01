package com.yonder.exercise.ui.books;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.orhanobut.hawk.Hawk;
import com.yonder.exercise.R;
import com.yonder.exercise.db.AppDatabase;
import com.yonder.exercise.db.BookModel;
import com.yonder.exercise.ui.detail.BookDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BooksActivity extends LifecycleActivity implements SwipeRefreshLayout.OnRefreshListener, BooksAdapter.OnItemClickListener {

    String TAG = BooksActivity.class.getSimpleName();


    BooksViewModel basicViewModel;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @BindView(R.id.lRefresh)
    SwipeRefreshLayout mSwipeRL;

    @BindView(R.id.bookNameEt)
    EditText bookNameEt;

    @BindView(R.id.fab)
    FloatingActionButton fab;


    Context context;
    BooksAdapter basicAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        ButterKnife.bind(this);
        context = this;
        Hawk.init(context).build();
        basicViewModel = ViewModelProviders.of(this).get(BooksViewModel.class);
        basicViewModel.getBooks().observe(BooksActivity.this, new Observer<List<BookModel>>() {
            @Override
            public void onChanged(@Nullable List<BookModel> books) {
                Log.i(TAG, "onChanged: ");
                mSwipeRL.setRefreshing(false);
                if (basicAdapter == null) {
                    basicAdapter = new BooksAdapter(books, BooksActivity.this);
                    recyclerView.setAdapter(basicAdapter);
                } else {
                    basicAdapter.setBooksItemList(books);
                    basicAdapter.notifyDataSetChanged();
                }
            }
        });
        mSwipeRL.setOnRefreshListener(this);
        mSwipeRL.setRefreshing(true);
    }


    @OnClick
    public void onClickSearchFab(View view) {
        loadBooks();
    }

    @Override
    public void onRefresh() {
        loadBooks();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }

    private void loadBooks() {
        Hawk.deleteAll();
        basicViewModel.deleteAll();
        basicViewModel.loadBooks(bookNameEt.getText().toString());
    }

    @Override
    public void onClick(BookModel bookModel) {
        startActivity(new Intent(this, BookDetailActivity.class).putExtra(BookDetailActivity.BOOK_ID, bookModel.getBookId()));
    }
}
