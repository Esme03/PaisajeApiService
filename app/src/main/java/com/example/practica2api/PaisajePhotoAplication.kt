package com.example.practica2api

import android.app.Application
import com.example.practica2api.data.AppContainer
import com.example.practica2api.data.DefaultAppContainer


class PaisajePhotoApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}