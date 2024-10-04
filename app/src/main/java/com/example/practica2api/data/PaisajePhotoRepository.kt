package com.example.practica2api.data

import com.example.practica2api.model.PaisajePhoto
import com.example.practica2api.network.PaisajeApiService


interface PaisajePhotoRepository{

    suspend fun  getPaisajePhoto(): List<PaisajePhoto>
}
class NetworkPaisajePhotoRepository(
    private val paisajeApiService: PaisajeApiService
) : PaisajePhotoRepository{

    override suspend fun getPaisajePhoto(): List<PaisajePhoto> = paisajeApiService.getPhotos()


}