package com.example.practica2api.network

import com.example.practica2api.model.PaisajePhoto

import retrofit2.http.GET

private const val BASE_URL = "https://picsum.photos/"



interface PaisajeApiService{
    @GET("v1/images/search?limit=10")
    suspend fun getPhotos():List<PaisajePhoto>
}
