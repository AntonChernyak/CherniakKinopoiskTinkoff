package com.example.domain.interactors

import com.example.domain.models.FilmItemDto
import com.example.domain.repository.FilmListRemoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FilmListInteractor(
    private val remoteRepository: FilmListRemoteRepository
) {

    private val mUiStateMutableFlow = MutableStateFlow(UIStateEnum.DEFAULT_STATE)
    val uiStateFlow: StateFlow<UIStateEnum> = mUiStateMutableFlow

    suspend fun getTopFilms(type: String, page: Int): List<FilmItemDto> {
        var topsList  = listOf<FilmItemDto>()
        mUiStateMutableFlow.value = UIStateEnum.START_LOADING
        try {
            mUiStateMutableFlow.value = UIStateEnum.NETWORK_AVAILABLE

            topsList = remoteRepository.getTopFilms(type, page)

            mUiStateMutableFlow.value = UIStateEnum.END_LOADING
        } catch (e: Exception) {
            mUiStateMutableFlow.value = UIStateEnum.NETWORK_ERROR
        }
        return topsList
    }
}