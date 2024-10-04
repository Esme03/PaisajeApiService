package com.example.practica2api.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.practica2api.PaisajePhotoApplication
import com.example.practica2api.data.PaisajePhotoRepository
import com.example.practica2api.model.PaisajePhoto

import kotlinx.coroutines.launch
import java.io.IOException

sealed interface PaisajeUiState{
    data class Success(val photos:List<PaisajePhoto>) : PaisajeUiState
    object Error: PaisajeUiState
    object Loading: PaisajeUiState
}
class PaisajeViewModel(private val paisajePhotoRepository: PaisajePhotoRepository):ViewModel(){ //insercón de independencias, para no modificar la clase principal mejor solo la inserción de la dependencia, lo pasamos como parámetro
    var paisajeUiState:PaisajeUiState  by mutableStateOf(PaisajeUiState.Loading)
        private set

    init{
        getPaisajePhotos()
    }

    private  fun getPaisajePhotos(){
        viewModelScope.launch {
            paisajeUiState = try {
                val listResult = paisajePhotoRepository.getPaisajePhoto()
                PaisajeUiState.Success(listResult)
            }  catch (e: IOException){
                PaisajeUiState.Error
            }

        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer {
                val application = (this[APPLICATION_KEY] as PaisajePhotoApplication)
                val paisajePhotoRepository = application.container.paisajePhotoRepository
                PaisajeViewModel(paisajePhotoRepository = paisajePhotoRepository)
            }
        }
    }

}