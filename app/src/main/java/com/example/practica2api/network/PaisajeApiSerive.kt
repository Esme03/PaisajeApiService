package com.example.practica2api.network

import com.example.practica2api.model.PaisajePhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://picsum.photos/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface PaisajeApiService { // Corregido el nombre
    @GET("v2/list?page=2&limit=10")
    suspend fun getPhotos(): List<PaisajePhoto>
}

object PaisajeApi {
    val retrofitService: PaisajeApiService by lazy {
        retrofit.create(PaisajeApiService::class.java)
    }
}
