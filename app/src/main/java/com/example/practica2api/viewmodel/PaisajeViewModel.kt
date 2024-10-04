package com.example.practica2api.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica2api.model.PaisajePhoto
import com.example.practica2api.network.PaisajeApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface PaisajeUiState {
    data class Success(val photos: List<PaisajePhoto>) : PaisajeUiState
    data class Error(val message: String) : PaisajeUiState // Añadir mensaje de error
    object Loading : PaisajeUiState
}

class PaisajeViewModel : ViewModel() {
    var paisajeUiState: PaisajeUiState by mutableStateOf(PaisajeUiState.Loading)
        private set

    init {
        getPaisajePhotos()
    }

    private fun getPaisajePhotos() {
        viewModelScope.launch {
            try {
                val listResult = PaisajeApi.retrofitService.getPhotos()
                paisajeUiState = PaisajeUiState.Success(listResult)
            } catch (e: Exception) { // Capturar todas las excepciones
                paisajeUiState = PaisajeUiState.Error(e.localizedMessage ?: "Error desconocido")
            }
        }
    }
}