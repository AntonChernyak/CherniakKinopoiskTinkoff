package com.example.cherniakkinopoisktinkoff.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactors.FilmListInteractor
import com.example.domain.models.FilmItemDto
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmListViewModel(
    private val listInteractor: FilmListInteractor
) : ViewModel() {

    private val mFilmsViewModel: MutableLiveData<List<FilmItemDto>> = MutableLiveData()
    val filmsViewModel: LiveData<List<FilmItemDto>> = mFilmsViewModel

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.message
    }

    fun getTopFilms(type: String = top100type, page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            mFilmsViewModel.postValue(listInteractor.getTopFilms(type, page))
        }
    }

    companion object {
        private const val top100type: String = "TOP_100_POPULAR_FILMS"
    }
}