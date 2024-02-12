package com.example.pixabayphoto.mockData

import com.example.pixabayphoto.data.network.dataSource.PhotosDataSource
import com.example.pixabayphoto.data.network.model.PhotosSearchApiModel
import com.example.pixabayphoto.utils.Result
import com.example.pixabayphoto.data.network.util.ErrorHandler


class MockPhotosDataSource(
    private val errorHandler: ErrorHandler,
    private val shouldThrowError: Boolean = false
) : PhotosDataSource {
    override suspend fun searchPhotos(searchTerm: String): Result<PhotosSearchApiModel> {
        return if (shouldThrowError) {
            Result.Error(errorHandler.handle(Exception("Photos not found")))
        } else
            Result.Success(FakePhotos.photos)
    }
}
