package com.example.pixabayphoto.features.search

import com.example.pixabayphoto.domain.Photo


/**
 * UI state for the Search.
 *
 * This is derived from [SearchViewModelState], but split into two possible subclasses to more
 * precisely represent the state available to render the UI.
 */
sealed interface SearchUiState {

    val isLoading: Boolean
    val errorMessages: String

    /**
     * There is no search input.
     *
     */
    data class NoSearchInput(
        val suggestions: List<String>,
        override val isLoading: Boolean,
        override val errorMessages: String,
    ) : SearchUiState

    /**
     * There is a search input.
     */
    data class HasSearchInput(
        val photos: List<Photo>,
        override val isLoading: Boolean,
        override val errorMessages: String,
    ) : SearchUiState
}

/**
 * representation of the Search state.
 */
data class SearchViewModelState(
    val suggestions: List<String> = emptyList(),
    val photos: List<Photo> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessages: String = ""
) {

    /**
     * Converts this [SearchViewModelState] into a more strongly typed [SearchUiState] for driving
     * the ui.
     */
    fun toUiState(): SearchUiState =
        if (photos.isEmpty()) {
            SearchUiState.NoSearchInput(
                isLoading = isLoading,
                errorMessages = errorMessages,
                suggestions = suggestions
            )
        } else {
            SearchUiState.HasSearchInput(
                isLoading = isLoading,
                errorMessages = errorMessages,
                photos = photos
            )
        }
}
