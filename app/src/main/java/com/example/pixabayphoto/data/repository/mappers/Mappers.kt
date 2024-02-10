package com.example.pixabayphoto.data.repository.mappers

import com.example.pixabayphoto.data.network.model.PhotosMetaDataApiModel
import com.example.pixabayphoto.data.network.model.PhotosSearchApiModel
import com.example.pixabayphoto.domain.Photo

/**
 * Converts the network model to the domain model
 */
internal fun PhotosSearchApiModel.toDomainModel(): List<Photo> =
    hits.map { it.toDomainModel() }

internal fun PhotosMetaDataApiModel.toDomainModel() =
    Photo(
        id = id.orEmpty(),
        user = user.orEmpty(),
        thumbnailUrl = previewURL.orEmpty(),
        largeImageURL = largeImageURL.orEmpty()
    )
