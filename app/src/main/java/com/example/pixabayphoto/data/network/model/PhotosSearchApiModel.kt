package com.example.pixabayphoto.data.network.model

import com.google.gson.annotations.SerializedName

data class PhotosSearchApiModel(
    @SerializedName("total") val total: Int? = null,
    @SerializedName("totalHits") val totalHits: Int? = null,
    @SerializedName("hits") val hits: ArrayList<PhotosMetaDataApiModel> = arrayListOf()
)

data class PhotosMetaDataApiModel(
    @SerializedName("id") val id: String? = null,
    @SerializedName("tags") val tags: String? = null,
    @SerializedName("previewURL") val previewURL: String? = null,
    @SerializedName("largeImageURL") val largeImageURL: String? = null,
    @SerializedName("views") val views: Int? = null,
    @SerializedName("downloads") val downloads: Int? = null,
    @SerializedName("likes") val likes: Int? = null,
    @SerializedName("comments") val comments: Int? = null,
    @SerializedName("user_id") val userId: Int? = null,
    @SerializedName("user") val user: String? = null,
    @SerializedName("userImageURL") val userImageURL: String? = null
)
