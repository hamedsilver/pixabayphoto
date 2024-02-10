package com.example.pixabayphoto.data.repository

import com.example.pixabayphoto.data.network.dataSource.PhotosDataSource
import com.example.pixabayphoto.data.repository.mappers.toDomainModel
import com.example.pixabayphoto.domain.Photo
import com.example.pixabayphoto.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * An interface to represent a repository that returns Photos,
 * and save user search terms.
 */
interface PhotosRepository {
    /**
     * Returns Photos.
     *
     * Assumptions:
     *
     *  - List of Photo returned based on search terms.
     */
    suspend fun search(searchTerms: String): Result<List<Photo>>
}

/**
 *  An implementation for the PhotosRepository which search's Photos from dataSource,
 *  and save users search terms in DB.
 */

internal class PhotosRepositoryImpl(
    private val dataSource: PhotosDataSource
) : PhotosRepository {

    override suspend fun search(searchTerms: String): Result<List<Photo>> =
        dataSource.searchPhotos(searchTerms).mapOnSuccess {
            it.toDomainModel()
        }
}
