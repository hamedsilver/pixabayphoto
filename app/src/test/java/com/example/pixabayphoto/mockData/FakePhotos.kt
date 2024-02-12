package com.example.pixabayphoto.mockData

import com.example.pixabayphoto.data.network.model.PhotosMetaDataApiModel
import com.example.pixabayphoto.data.network.model.PhotosSearchApiModel

object FakePhotos {
    val photos = PhotosSearchApiModel(
        total = 1,
        totalHits = 1,
        hits = arrayListOf(
            PhotosMetaDataApiModel(
                id = "1",
                tags = "",
                previewURL = "https://pixabay.com/get/ga8a79cc84fedf181716caf6beaff9811c21100a51e0833c8c3b3af3fef39421f4723e94ff04c84addfd96cca431c54a3dadd93f57c22aaacebfb449589b8c748_1280.jpg",
                largeImageURL = "https://pixabay.com/get/ga8a79cc84fedf181716caf6beaff9811c21100a51e0833c8c3b3af3fef39421f4723e94ff04c84addfd96cca431c54a3dadd93f57c22aaacebfb449589b8c748_1280.jpg",
                views = 1,
                downloads = 1,
                likes = 1,
                comments = 1,
                userId = 1,
                user = "",
                userImageURL = "",
            )
        )
    )
}
