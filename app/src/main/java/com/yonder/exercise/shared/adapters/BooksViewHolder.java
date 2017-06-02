package com.yonder.exercise.shared.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yonder.exercise.R;

/**
 * Created by YusufMac on 30/05/17.
 */

class BooksViewHolder extends RecyclerView.ViewHolder {

    private TextView tvName;
    private TextView tvDescription;
    private CardView tvCardView;

    BooksViewHolder(View view) {
        super(view);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        tvCardView = (CardView) view.findViewById(R.id.tvCardView);
    }

    TextView getTvDescription() {
        return tvDescription;
    }

    TextView getTvName() {
        return tvName;
    }


    CardView getTvCardView() {
        return tvCardView;
    }
}
