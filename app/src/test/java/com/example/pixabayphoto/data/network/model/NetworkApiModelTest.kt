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
            previewURL = "https://pixabay.com/get/ga8a79cc84fedf181716caf6beaff9811c21100a51e0833c8c3b3af3fef39421f4723e94ff04c84addfd96cca431c54a3dadd93f57c22aaacebfb449589b8c748_1280.jpg",
            largeImageURL = "https://cdn.pixabay.com/photo/2017/05/11/19/44/fresh-fruits-2305192_150.jpg",
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
        assertEquals("https://pixabay.com/get/ga8a79cc84fedf181716caf6beaff9811c21100a51e0833c8c3b3af3fef39421f4723e94ff04c84addfd96cca431c54a3dadd93f57c22aaacebfb449589b8c748_1280.jpg", entity.largeImageURL)
        assertEquals("https://cdn.pixabay.com/photo/2017/05/11/19/44/fresh-fruits-2305192_150.jpg", entity.thumbnailUrl)
    }
}