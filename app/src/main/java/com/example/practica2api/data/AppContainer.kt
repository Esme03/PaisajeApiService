package com.example.practica2api.data

import com.example.practica2api.network.PaisajeApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val paisajePhotoRepository: PaisajePhotoRepository
}
class DefaultAppContainer: AppContainer{
    private val baseUrl = "https://picsum.photos/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).
        baseUrl(baseUrl).build()
    private val retrofitService: PaisajeApiService by lazy {
        retrofit.create(PaisajeApiService:: class.java)
    }
    override val paisajePhotoRepository: PaisajePhotoRepository by lazy{
        NetworkPaisajePhotoRepository(retrofitService)
    }

}