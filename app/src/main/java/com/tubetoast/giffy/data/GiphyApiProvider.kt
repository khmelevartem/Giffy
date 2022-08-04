package com.tubetoast.giffy.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class GiphyApiProvider {

    val api: GiphyApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(createClient())
        .addConverterFactory(JacksonConverterFactory.create())
        .build().create(GiphyApi::class.java)

    private fun createClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                Interceptor { chain ->
                    val request = chain.request().run {
                        val urlWithKey = url.newBuilder()
                            .addQueryParameter(KEY_PARAM, KEY_VALUE)
                            .build()
                        newBuilder()
                            .url(urlWithKey)
                            .build()
                    }
                    chain.proceed(request)
                }
            )
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BASIC)
            ).build()

    private companion object {
        const val BASE_URL = "https://api.giphy.com/v1/"
        const val KEY_PARAM = "api_key"
        const val KEY_VALUE = "f0Ui0zVtxaLYc0xFScI5lnFzU2OF8WJu"
    }
}