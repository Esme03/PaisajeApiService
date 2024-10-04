package com.example.practica2api.fake


import com.example.practica2api.rules.TestDispatcherRule
import com.example.practica2api.viewmodel.PaisajeUiState
import com.example.practica2api.viewmodel.PaisajeViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule


class PaisajeViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()
    @Test
    fun PaisajeViewModel_getPaisajePhotos_verifyPaisajeUiStateSucces(){


        runTest {
            val paisajeViewModel = PaisajeViewModel(
                paisajePhotoRepository = FakeNetworkPaisajePhotoRepository()
            )
            assertEquals(PaisajeUiState.Success(FakeDataSource.photoList),paisajeViewModel.paisajeUiState)

        }


    }

}