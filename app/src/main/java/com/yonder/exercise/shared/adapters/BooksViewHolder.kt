package com.yonder.exercise.shared.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.yonder.exercise.R

/**
 * Created by YusufMac on 30/05/17.
 */

 class BooksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tvName: TextView
    val tvDescription: TextView
    val tvCardView: CardView

    init {
        tvName = view.findViewById<View>(R.id.tvName) as TextView
        tvDescription = view.findViewById<View>(R.id.tvDescription) as TextView
        tvCardView = view.findViewById<View>(R.id.tvCardView) as CardView
    }
}
