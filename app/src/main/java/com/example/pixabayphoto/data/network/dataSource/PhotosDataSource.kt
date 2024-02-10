package com.example.pixabayphoto.data.network.dataSource

import com.example.pixabayphoto.data.network.model.PhotosSearchApiModel
import com.example.pixabayphoto.data.network.service.PhotoService
import com.example.pixabayphoto.data.network.util.ErrorHandler
import com.example.pixabayphoto.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * An interface to represent a dataSource that returns a [PhotosSearchApiModel].
 *  * Note:  Relying on interfaces makes API implementations swappable.
 *      In addition to providing scalability and allowing to replace dependencies more easily,
 *      it also favors testability, we can inject fake data source implementations in tests.
 */
interface PhotosDataSource {
    suspend fun searchPhotos(searchTerm: String): Result<PhotosSearchApiModel>
}

/**
 *  An implementation for the PhotosDataSource which returns [PhotosSearchApiModel] based on Network Data.
 *
 *  * Note: this dataSource uses Retrofit Interface for networking, Although retrofit handles threading
 *      internally and main-safe but PhotoSearchDataSource handles thread it self with CoroutineDispatcher
 *      for the case of replacing retrofit or implement networking with HttpURLConnection.
 *      https://developer.android.com/reference/java/net/HttpURLConnection
 */
internal class PhotosNetworkDataSource(
    private val service: PhotoService,
    private val errorHandler: ErrorHandler,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PhotosDataSource {
    override suspend fun searchPhotos(searchTerm: String): Result<PhotosSearchApiModel> {
        return try {
            withContext(dispatcher) {
                Result.Success(service.fetchPhotos(searchTerm = searchTerm))
            }
        } catch (e: Exception) {
            Result.Error(errorHandler.handle(e))
        }
    }
}