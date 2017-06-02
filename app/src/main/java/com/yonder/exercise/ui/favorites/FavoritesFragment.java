package com.yonder.exercise.ui.favorites;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.hawk.Hawk;
import com.yonder.exercise.R;
import com.yonder.exercise.db.BookModel;
import com.yonder.exercise.shared.adapters.BooksRecyclerViewAdapter;
import com.yonder.exercise.shared.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoritesFragment extends LifecycleFragment implements BooksRecyclerViewAdapter.OnItemClickListener {


    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    FavoritesViewModel favoriteViewModel;

    BooksRecyclerViewAdapter booksRecyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, view);
        favoriteViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);
        Hawk.init(getContext()).build();
        favoriteViewModel.getFavoritedBooks().observe(this, books -> {
            if (booksRecyclerViewAdapter == null) {
                booksRecyclerViewAdapter = new BooksRecyclerViewAdapter(books, FavoritesFragment.this);
                recyclerView.setAdapter(booksRecyclerViewAdapter);
            } else {
                booksRecyclerViewAdapter.setBooksItemList(books);
                booksRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }


    @Override
    public void onClick(BookModel bookModel) {
        ToastUtil.newInstance(getContext()).showToast(bookModel.getBookTitle());
    }

}
