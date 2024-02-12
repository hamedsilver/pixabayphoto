package com.example.pixabayphoto.domain

/**
 * A data class that holds details about a Photo.
 *
 * @property id: String – ID of the Photo.
 * @property user: String – Title of the Photo.
 * @property thumbnail_url: String - Thumbnail Url of the Photo.
 * @property largeImageURL: String - Image Url of the Photo(Large size).
 * @property likes: Int - The number of likes..
 * @property downloads: Int - The number of downloads.
 * @property comments: Int - The number of comments.
 *
 */
data class Photo(
    val id: String,
    val user: String,
    val thumbnailUrl: String,
    val largeImageURL: String,
    val likes: Int,
    val downloads: Int,
    val comments: Int,
    val likes_formated: String,
    val downloads_formated: String,
    val comments_formated: String,
)
