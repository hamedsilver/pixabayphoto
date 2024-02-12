package com.example.pixabayphoto.mockData

import com.example.pixabayphoto.data.local.model.SearchSuggestionEntity
import com.example.pixabayphoto.data.network.dataSource.PhotosDataSource
import com.example.pixabayphoto.data.repository.PhotosRepository
import com.example.pixabayphoto.data.repository.mappers.toDomainModel
import com.example.pixabayphoto.utils.Result
import com.example.pixabayphoto.domain.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MockPhotoRepository(
    private val searchSuggestionDao: MockSearchSuggestionDao,
    private val dataSource: PhotosDataSource
) : PhotosRepository {
    override suspend fun search(searchTerms: String): Result<List<Photo>> {
        return dataSource.searchPhotos(searchTerms).mapOnSuccess { it.toDomainModel() }
    }

    override suspend fun saveSearchTerm(searchTerms: String) {
        searchSuggestionDao.insertSearchTerms(SearchSuggestionEntity(searchTerm = searchTerms))
    }

    override suspend fun getSearchSuggestions(): Flow<List<String>> =
        searchSuggestionDao.loadAllSearchTerms().map { it.map { it.searchTerm } }
}
