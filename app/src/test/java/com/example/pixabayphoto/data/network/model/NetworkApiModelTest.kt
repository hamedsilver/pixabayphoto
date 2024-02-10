package com.example.pixabayphoto.data.network.model

import com.example.pixabayphoto.data.repository.mappers.toDomainModel
import com.example.pixabayphoto.mockData.FakePhotos
import org.junit.Assert.*
import org.junit.Test

class NetworkApiModelTest{

    @Test
    fun network_PhotosMetaDataApiModel_can_be_mapped_to_domain_photo() {
        val networkModel = FakePhotos.photos
        val entity = networkModel

        assertEquals(1, entity.total)
        assertEquals(1, entity.totalHits)
        assertEquals(listOf(PhotosMetaDataApiModel(
            id = "1",
            tags = "",
            previewURL = "",
            largeImageURL = "",
            views = 1,
            downloads = 1,
            likes = 1,
            comments = 1,
            userId = 1,
            user = "",
            userImageURL = "",
        )), entity.hits)
    }

    @Test
    fun network_PhotoApiModel_can_be_mapped_to_domain_photo() {
        val networkModel = FakePhotos.photos.hits
        val entity = networkModel.map { it.toDomainModel() }.first()

        assertEquals("test_id", entity.id)
        assertEquals("test_title", entity.user)
        assertEquals("https://farm1.staticflickr.com/test_server/test_id_test_secret_b.jpg", entity.largeImageURL)
        assertEquals("https://farm1.staticflickr.com/test_server/test_id_test_secret_t.jpg", entity.thumbnailUrl)
    }
}