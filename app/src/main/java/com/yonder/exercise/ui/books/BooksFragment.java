package com.yonder.exercise.ui.books;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.orhanobut.hawk.Hawk;
import com.yonder.exercise.R;
import com.yonder.exercise.db.BookModel;

import com.yonder.exercise.shared.adapters.BooksRecyclerViewAdapter;
import com.yonder.exercise.ui.detail.BookDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BooksFragment extends LifecycleFragment implements BooksRecyclerViewAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    String TAG = BooksFragment.class.getSimpleName();


    int index = 0;

    BooksViewModel basicViewModel;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @BindView(R.id.lRefresh)
    SwipeRefreshLayout mSwipeRL;

    @BindView(R.id.bookNameEt)
    EditText bookNameEt;

    @BindView(R.id.fab)
    FloatingActionButton fab;


    List<BookModel> books;

    Context context;
    BooksRecyclerViewAdapter basicAdapter;

    View view;

    public static BooksFragment newInstance() {
        return new BooksFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_books, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();
        Hawk.init(context).build();
        basicViewModel = ViewModelProviders.of(this).get(BooksViewModel.class);
        basicViewModel.getAllBooks().observe(BooksFragment.this, books -> {
            this.books = books;
            mSwipeRL.setRefreshing(false);

            if (basicAdapter == null) {
                basicAdapter = new BooksRecyclerViewAdapter(books, BooksFragment.this);
                recyclerView.setAdapter(basicAdapter);
            } else {
                basicAdapter.setBooksItemList(books);
                basicAdapter.notifyDataSetChanged();
            }
        });
        mSwipeRL.setOnRefreshListener(this);
        fab.setOnClickListener(view -> loadBooks());
        return view;
    }


    private void loadBooks() {
        String query = bookNameEt.getText().toString();
        if (TextUtils.isEmpty(query))
            return;
        mSwipeRL.setRefreshing(true);
        Hawk.deleteAll();
        basicViewModel.deleteAllBooks();
        basicViewModel.loadBooks(query);
    }

    @Override
    public void onClick(BookModel bookModel) {
        startActivity(new Intent(context, BookDetailActivity.class).putExtra(BookDetailActivity.BOOK_ID, bookModel.getBookId()));
    }

    @Override
    public void onRefresh() {

        new Handler().postDelayed(() -> mSwipeRL.setRefreshing(false), 1000);
    }
}
