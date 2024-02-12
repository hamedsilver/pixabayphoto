package com.example.pixabayphoto.data.repository.mappers

import com.example.pixabayphoto.data.network.model.PhotosMetaDataApiModel
import com.example.pixabayphoto.data.network.model.PhotosSearchApiModel
import com.example.pixabayphoto.domain.Photo
import java.text.NumberFormat
import java.util.Locale

/**
 * Converts the network model to the domain model
 */
internal fun PhotosSearchApiModel.toDomainModel(): List<Photo> =
    hits.map { it.toDomainModel() }

internal fun PhotosMetaDataApiModel.toDomainModel(): Photo {
    val formatter = NumberFormat.getNumberInstance(Locale.US)
    return Photo(
        id = id.orEmpty(),
        user = user.orEmpty(),
        thumbnailUrl = previewURL.orEmpty(),
        largeImageURL = largeImageURL.orEmpty(),
        likes = likes ?: 0,
        downloads = downloads ?: 0,
        comments = comments ?: 0,
        likes_formated = formatter.format(likes),
        downloads_formated = formatter.format(downloads),
        comments_formated = formatter.format(comments)
    )

}

