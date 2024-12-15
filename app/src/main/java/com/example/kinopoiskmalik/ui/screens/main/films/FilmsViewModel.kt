package com.example.kinopoiskmalik.ui.screens.main.films

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskmalik.data.collectAsResult
import com.example.kinopoiskmalik.domain.models.Film
import com.example.kinopoiskmalik.domain.models.FilmDetail
import com.example.kinopoiskmalik.domain.repository.FilmQueryType
import com.example.kinopoiskmalik.domain.repository.FilmRepository
import com.example.kinopoiskmalik.utils.EffectHandler
import com.example.kinopoiskmalik.utils.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SEARCH_TIME_DELAY = 500L

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val filmRepository: FilmRepository,
) : ViewModel(), EventHandler<FilmsEvent>, EffectHandler<FilmsEffect> {

    private val _filmsUiState = MutableStateFlow(FilmsUiState())
    val filmsUiState = _filmsUiState.asStateFlow()

    override val effectChannel: Channel<FilmsEffect> = Channel()

    private var searchJob: Job? = null

    init {
        getFilms()
    }

    override fun obtainEvent(event: FilmsEvent) {
        when (event) {
            FilmsEvent.RefreshData -> {
                getFilms()
            }

            is FilmsEvent.ChangeSearchBarText -> {
                changeSearchBarText(event.value)
            }

            is FilmsEvent.SelectedFilm -> {
                getFilmDetail(event.id)
            }

            is FilmsEvent.RefreshFilmDetail -> {
                getFilmDetail(event.id)
            }

            FilmsEvent.ClearSelectedFilm -> {
                clearSelectedFilm()
            }
        }
    }

    private fun changeSearchBarText(value: String) {
        _filmsUiState.update { currentState ->
            currentState.copy(
                searchBarText = value,
            )
        }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_TIME_DELAY)
            getFilms(value)
        }
    }

    private fun clearSelectedFilm() {
        _filmsUiState.update { currentState ->
            currentState.copy(
                filmDetailLoading = false,
                errorFilmDetail = null,
                selectedFilm = null,
            )
        }
    }

    private fun getFilmDetail(id: String) {
        viewModelScope.launch {
            filmRepository.getFilmDetail(
                id = id,
                force = false,
            ).collectAsResult(
                onSuccess = { filmDetail ->
                    _filmsUiState.update { currentState ->
                        currentState.copy(
                            filmDetailLoading = false,
                            errorFilmDetail = null,
                            selectedFilm = filmDetail,
                        )
                    }
                },
                onError = { ex, message ->
                    _filmsUiState.update { currentState ->
                        currentState.copy(
                            filmDetailLoading = false,
                            errorFilmDetail = message,
                        )
                    }
                    sendEffect(FilmsEffect.ShowToast(message.toString()))
                },
                onLoading = {
                    _filmsUiState.update { currentState ->
                        currentState.copy(
                            filmDetailLoading = true,
                            errorFilmDetail = null,
                        )
                    }
                }
            )
        }
    }

    private fun getFilms(
        keyWord: String = "",
    ) {
        viewModelScope.launch {
            filmRepository.getFilms(
                queryType = FilmQueryType.TOP_100,
                keyWord = keyWord,)
                .collectAsResult(
                onSuccess = { films ->
                    _filmsUiState.update { currentState ->
                        currentState.copy(
                            films = films,
                            filmsLoading = false,
                            error = null,
                        )
                    }
                },
                onError = { ex, message ->
                    _filmsUiState.update { currentState ->
                        currentState.copy(
                            filmsLoading = false,
                            error = message,
                        )
                    }
                },
                onLoading = {
                    _filmsUiState.update { currentState ->
                        currentState.copy(
                            filmsLoading = true,
                            error = null,
                        )
                    }
                }
            )
        }
    }

}

data class FilmsUiState(
    val filmsLoading: Boolean = false,
    val filmDetailLoading: Boolean = false,
    val error: String? = null,
    val errorFilmDetail: String? = null,
    val films: List<Film> = emptyList(),
    val searchBarText: String = "",
    val selectedFilm: FilmDetail? = null,
)

sealed interface FilmsEffect {
    data class ShowToast(
        val message: String,
    ) : FilmsEffect
}

sealed interface FilmsEvent {
    data object RefreshData : FilmsEvent

    data class ChangeSearchBarText(
        val value: String,
    ) : FilmsEvent

    data class SelectedFilm(
        val id: String,
    ) : FilmsEvent

    data class RefreshFilmDetail(val id: String) : FilmsEvent

    data object ClearSelectedFilm : FilmsEvent
}