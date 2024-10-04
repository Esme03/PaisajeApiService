package com.example.practica2api.fake


import com.example.practica2api.data.PaisajePhotoRepository
import com.example.practica2api.model.PaisajePhoto


class FakeNetworkPaisajePhotoRepository: PaisajePhotoRepository {
    override suspend fun getPaisajePhoto(): List<PaisajePhoto> {
        return FakeDataSource.photoList
    }
}