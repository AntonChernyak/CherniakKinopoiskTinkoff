package com.example.cherniakkinopoisktinkoff.presentation.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.cherniakkinopoisktinkoff.R

fun ImageView.loadImage(imageUrl: String = ""){
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_movie_placeholder)
        .error(R.drawable.ic_poster_error)
        .into(this)
}