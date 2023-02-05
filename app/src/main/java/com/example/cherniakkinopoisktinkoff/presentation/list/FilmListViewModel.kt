package com.example.cherniakkinopoisktinkoff.presentation.list

import android.util.Log
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

    private val mFilmsLiveData: MutableLiveData<List<FilmItemDto>> = MutableLiveData()
    val filmsLiveData: LiveData<List<FilmItemDto>> = mFilmsLiveData

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.message
        Log.d("TAGGGGGG", "err = ${throwable.message}" )
    }

    init {
        getTopFilms()
    }

    fun getTopFilms(type: String = top100type, page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            mFilmsLiveData.postValue(listInteractor.getTopFilms(type, page))
        }
    }

    companion object {
        private const val top100type: String = "TOP_100_POPULAR_FILMS"
    }
}