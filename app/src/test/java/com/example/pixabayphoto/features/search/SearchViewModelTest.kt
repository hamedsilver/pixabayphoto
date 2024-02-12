package com.example.pixabayphoto.features.search

import com.example.pixabayphoto.data.network.dataSource.PhotosDataSource
import com.example.pixabayphoto.data.network.util.ErrorHandlerImpl
import com.example.pixabayphoto.data.repository.PhotosRepository
import com.example.pixabayphoto.data.repository.mappers.toDomainModel
import com.example.pixabayphoto.mockData.FakePhotos
import com.example.pixabayphoto.mockData.MockPhotoRepository
import com.example.pixabayphoto.mockData.MockPhotosDataSource
import com.example.pixabayphoto.mockData.MockSearchSuggestionDao
import com.example.pixabayphoto.rules.TestDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest{
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var searchSuggestionDao: MockSearchSuggestionDao
    private lateinit var dataSource: PhotosDataSource
    private lateinit var repository: PhotosRepository
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        dataSource = MockPhotosDataSource(ErrorHandlerImpl())
        searchSuggestionDao = MockSearchSuggestionDao()
        repository = MockPhotoRepository(searchSuggestionDao, dataSource)
        viewModel = SearchViewModel(repository = repository)
    }

    @Test
    fun uiState_whenInitialized_thenShowLoading() = runTest {
        assertEquals(SearchUiState.NoSearchInput(isLoading = false, errorMessages ="", suggestions = emptyList()), viewModel.uiState.value)
    }

    @Test
    fun uiState_whenInitialized_then_collect_searchTerms() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        repository.saveSearchTerm("1")
        assertEquals(SearchUiState.NoSearchInput(isLoading = false, errorMessages = "", suggestions = listOf("1")), viewModel.uiState.value)

        collectJob.cancel()
    }

    @Test
    fun uiState_whenSearchTerm_isEntered_then_state_change_to_HasSearchInput() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        viewModel.searchTerms.value = "abc"
        delay(800)
        assertEquals(SearchUiState.HasSearchInput(photos= FakePhotos.photos.hits.map { it.toDomainModel() }, isLoading = false, errorMessages = ""), viewModel.uiState.value)

        collectJob.cancel()
    }
}