package com.example.cherniakkinopoisktinkoff.presentation.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.cherniakkinopoisktinkoff.databinding.ItemFilmBinding
import com.example.cherniakkinopoisktinkoff.presentation.extensions.loadImage
import com.example.domain.models.FilmItemDto

class FilmItemViewHolder(
    private val itemBinding: ItemFilmBinding,
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: FilmItemDto) {
        itemBinding.titleTextView.text = item.title
        itemBinding.yearTextView.text = item.year

        itemBinding.posterImageView.loadImage(item.poster)

        //itemBinding.favouritesLabelImageView.visibility = if (true) View.VISIBLE else View.GONE
    }
}