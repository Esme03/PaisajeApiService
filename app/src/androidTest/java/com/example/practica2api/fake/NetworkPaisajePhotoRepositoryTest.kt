package com.example.practica2api.fake

import com.example.practica2api.data.NetworkPaisajePhotoRepository

import kotlinx.coroutines.test.runTest
import org.junit.Test;
import org.junit.Assert.*

class NetworkPaisajePhotoRepositoryTest {
    @Test
    fun networkPaisajePhotoRepository_getPaisajePhotos_verifyPhotplist(){
        runTest { //sirve para probar las corrutines
            val repository = NetworkPaisajePhotoRepository(
                paisajeApiService = FakePaisajeApiService()
            )
            assertEquals(FakeDataSource.photoList, repository.getPaisajePhoto())
        }


    }
}