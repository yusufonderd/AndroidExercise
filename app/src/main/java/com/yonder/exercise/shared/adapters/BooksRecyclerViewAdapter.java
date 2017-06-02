package com.yonder.exercise.shared.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yonder.exercise.R;
import com.yonder.exercise.db.BookModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YusufMac on 30/05/17.
 */

public class BooksRecyclerViewAdapter extends RecyclerView.Adapter<BooksViewHolder> {

    private List<BookModel> booksItemList;
    private OnItemClickListener iBookModel;

    public BooksRecyclerViewAdapter(List<BookModel> booksItemList, OnItemClickListener iBookModel) {
        setBooksItemList(booksItemList);
        this.iBookModel = iBookModel;
    }

    public void setBooksItemList(List<BookModel> booksItemList) {
        if (booksItemList == null) this.booksItemList = new ArrayList<>();
        else this.booksItemList = booksItemList;
    }

    @Override
    public BooksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_book, parent, false);
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BooksViewHolder holder, final int position) {
        final BookModel bookModel = booksItemList.get(position);
        holder.getTvDescription().setText(bookModel.getBookTitle());
        holder.getTvName().setText(bookModel.getBookAuthor());
        holder.getTvCardView().setOnClickListener(view -> iBookModel.onClick(bookModel));
    }


    @Override
    public int getItemCount() {
        return booksItemList.size();
    }

    public interface OnItemClickListener {
        void onClick(BookModel bookModel);

    }
}


