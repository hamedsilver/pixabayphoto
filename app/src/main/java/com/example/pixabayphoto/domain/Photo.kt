package com.example.pixabayphoto.domain

/**
 * A data class that holds details about a Photo.
 *
 * @property id: String – ID of the Photo.
 * @property user: String – Title of the Photo.
 * @property thumbnail_url: String - Thumbnail Url of the Photo.
 * @property largeImageURL: String - Image Url of the Photo(Large size).
 *
 */
data class Photo(
    val id: String,
    val user: String,
    val thumbnailUrl: String,
    val largeImageURL: String,
)
