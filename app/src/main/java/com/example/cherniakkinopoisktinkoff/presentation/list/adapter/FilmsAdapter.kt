package com.example.cherniakkinopoisktinkoff.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cherniakkinopoisktinkoff.databinding.ItemFilmBinding
import com.example.domain.models.FilmItemDto

class FilmsAdapter(
    val itemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<FilmItemViewHolder>() {

    var data: List<FilmItemDto> = emptyList()
        set(newValue) {
            val diffCallback = FilmDiffItemCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemViewHolder {
        val viewBinding =
            ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmItemViewHolder(viewBinding, itemClick)
    }

    override fun onBindViewHolder(holder: FilmItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size
}