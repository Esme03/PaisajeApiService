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
    object Error : PaisajeUiState
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
            paisajeUiState = try {
                val listResult = PaisajeApi.retrofitService.getPhotos()
                PaisajeUiState.Success(listResult)
            } catch (e: IOException) {
                PaisajeUiState.Error
            }
        }
    }
}
