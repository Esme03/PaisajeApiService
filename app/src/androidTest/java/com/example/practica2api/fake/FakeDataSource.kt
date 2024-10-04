package com.example.practica2api.fake

import com.example.practica2api.model.PaisajePhoto


object FakeDataSource{

    const val idOne = "idCatOne"
    const val idTwo = "idCatTwo"
    const val imgOne = "https://urlOne"
    const val imgTwo = "https://urlTwo"
    const val widthOne= 512
    const val heightOne = 512
    const val widthTwo= 256
    const val heightTwo = 256

    val photoList = listOf(
        PaisajePhoto(
            idOne, imgOne, widthOne, heightOne),
        PaisajePhoto(idTwo, imgTwo, widthTwo, heightTwo)
    )
}