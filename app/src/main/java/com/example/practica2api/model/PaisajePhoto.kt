package com.example.practica2api.model
import kotlinx.serialization.Serializable

@Serializable
data class PaisajePhoto(
    val id: String,
    val author: String, // Opcional, si lo necesitas
    val width: Int,
    val height: Int,

)
