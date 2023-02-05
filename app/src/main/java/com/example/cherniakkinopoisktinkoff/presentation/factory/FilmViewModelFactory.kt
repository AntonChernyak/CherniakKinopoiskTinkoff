package com.example.cherniakkinopoisktinkoff.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cherniakkinopoisktinkoff.presentation.details.FilmDetailsViewModel
import com.example.cherniakkinopoisktinkoff.presentation.list.FilmListViewModel
import com.example.domain.interactors.FilmDetailsInteractor
import com.example.domain.interactors.FilmListInteractor
import java.lang.IllegalStateException

class FilmViewModelFactory(
    private val listInteractor: FilmListInteractor,
    private val detailsInteractor: FilmDetailsInteractor
) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass) {
            FilmListViewModel::class.java -> FilmListViewModel(listInteractor)
            FilmDetailsViewModel::class.java -> FilmDetailsViewModel(detailsInteractor)
            else -> throw IllegalStateException("Unknown ViewModel class")
        }
        return viewModel as T
    }

}