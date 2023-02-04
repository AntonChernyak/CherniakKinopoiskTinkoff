package com.example.cherniakkinopoisktinkoff.presentation.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.models.FilmItemDto

class FilmDiffItemCallback(
    private val oldList: List<FilmItemDto>,
    private val newList: List<FilmItemDto>,
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}