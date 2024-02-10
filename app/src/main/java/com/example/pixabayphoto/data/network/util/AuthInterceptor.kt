package com.example.pixabayphoto.data.network.util

import com.example.pixabayphoto.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request()
        val url = currentRequest.url.newBuilder()
            .addQueryParameter("image_type", "photo")
            .addQueryParameter("key", BuildConfig.API_KEY)
            .build()
        val newRequest = currentRequest.newBuilder()
            .url(url)
            .build()
        return chain.proceed(newRequest)
    }
}
