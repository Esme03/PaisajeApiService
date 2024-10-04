package com.example.practica2api.fake

import com.example.practica2api.model.PaisajePhoto
import com.example.practica2api.network.PaisajeApiService


class FakePaisajeApiService: PaisajeApiService {
    override suspend fun getPhotos(): List<PaisajePhoto> {
        return FakeDataSource.photoList
    }
}