package com.example.cherniakkinopoisktinkoff.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactors.FilmDetailsInteractor
import com.example.domain.models.FilmDetailsDto
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmDetailsViewModel(
    private val detailsInteractor: FilmDetailsInteractor
) : ViewModel() {

    private val mFilmLiveData: MutableLiveData<FilmDetailsDto> = MutableLiveData()
    val filmLiveData: LiveData<FilmDetailsDto> = mFilmLiveData

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.message
    }

    fun getFilmById(id: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            mFilmLiveData.postValue(detailsInteractor.getFilmDetails(id))
        }
    }
}