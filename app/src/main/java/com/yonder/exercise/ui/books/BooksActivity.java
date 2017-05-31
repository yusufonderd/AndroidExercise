package com.yonder.exercise.ui.books;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.yonder.exercise.R;
import com.yonder.exercise.db.AppDatabase;
import com.yonder.exercise.db.BookModel;
import com.yonder.exercise.ui.detail.BookDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BooksActivity extends LifecycleActivity implements SwipeRefreshLayout.OnRefreshListener, BooksAdapter.IBookModel {

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

    BooksAdapter basicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        ButterKnife.bind(this);

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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                basicViewModel.deleteAll();
                basicViewModel.loadBooks(bookNameEt.getText().toString());
            }
        });
        mSwipeRL.setOnRefreshListener(this);
        mSwipeRL.setRefreshing(true);
    }

    @Override
    public void onRefresh() {
        basicViewModel.loadBooks(bookNameEt.getText().toString());
        mSwipeRL.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }

    @Override
    public void onClickedBookModel(BookModel bookModel) {

        Log.i(TAG, "onClickedBookModel: " + bookModel.getBookId());


        startActivity(new Intent(this, BookDetailActivity.class).putExtra(BookDetailActivity.BOOK_ID, bookModel.getBookId()));

        Snackbar.make(fab, bookModel.getBookTitle(), Snackbar.LENGTH_SHORT).show();
    }
}
