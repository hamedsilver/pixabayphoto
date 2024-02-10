package com.example.pixabayphoto.di

import android.content.Context
import com.example.pixabayphoto.data.network.dataSource.PhotosDataSource
import com.example.pixabayphoto.data.network.dataSource.PhotosNetworkDataSource
import com.example.pixabayphoto.data.network.service.PhotoService
import com.example.pixabayphoto.data.network.util.ErrorHandler
import com.example.pixabayphoto.data.network.util.ErrorHandlerImpl
import com.example.pixabayphoto.data.repository.PhotosRepository
import com.example.pixabayphoto.data.repository.PhotosRepositoryImpl
import com.example.pixabayphoto.features.search.SearchViewModelFactory


/**
 * A Container for the Dependency Injection.
 * We can use Library's like Dagger, hilt, koin , ...
 * According to the scope of the project I prefer to do the manual way of DI
 * And don't use a library. ¯\_(ツ)_/¯
 */
class AppContainer(context: Context) {
    //Service
    private val service: PhotoService by lazy { PhotoService.create() }

    //Error Handler
    private val errorHandler: ErrorHandler by lazy { ErrorHandlerImpl() }


    //Datasource
    private val dataSource: PhotosDataSource by lazy { PhotosNetworkDataSource(service, errorHandler) }

    //Repository
    private val photosRepository: PhotosRepository by lazy { PhotosRepositoryImpl(dataSource) }

    //Presentation - viewModelFactory
    val searchViewModelFactory by lazy { SearchViewModelFactory(photosRepository) }
}
