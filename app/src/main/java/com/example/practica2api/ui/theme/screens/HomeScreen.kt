package com.example.practica2api.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.practica2api.R
import com.example.practica2api.model.PaisajePhoto
import com.example.practica2api.ui.theme.PaisajeApp
import com.example.practica2api.viewmodel.PaisajeUiState

@Composable
fun HomeScreen(
    paisajeUiState: PaisajeUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (paisajeUiState) { // Corregido para usar la instancia
        is PaisajeUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is PaisajeUiState.Success -> PhotosGridScreen(
            photos = paisajeUiState.photos,
            modifier = Modifier.fillMaxSize(),
            contentPadding = contentPadding
        )
        is PaisajeUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun PhotosGridScreen(
    photos: List<PaisajePhoto>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp),
        contentPadding = contentPadding
    ) {
        items(
            items = photos,
            key = { photo -> photo.id }
        ) { photo ->
            PaisajePhotoCard(
                photo = photo,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}

@Composable
fun PaisajePhotoCard(photo: PaisajePhoto, modifier: Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(photo.download_url) // Corregido para usar download_url
            .crossfade(true)
            .build(),
        contentDescription = stringResource(id = R.string.paisaje_image),
        modifier = modifier,
        error = painterResource(id = R.drawable.error_404),
        placeholder = painterResource(id = R.drawable.carga),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.loader),
            contentDescription = "Loading"
        )
    }
}

@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = photos)
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error_load),
            contentDescription = "Error Loading"
        )
        Text(text = stringResource(R.string.problem_with_connection))
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    // Asegúrate de usar el tema correcto
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            // Crear una lista de fotos de muestra para la previsualización
            val samplePhotos = List(10) { index ->
                PaisajePhoto(
                    id = index.toString(),
                    author = "Autor $index",
                    width = 2000,
                    height = 1500,
                    url = "https://unsplash.com/photos/$index",
                    download_url = "https://picsum.photos/id/${index + 1}/200/300"
                )
            }
            HomeScreen(
                paisajeUiState = PaisajeUiState.Success(samplePhotos)
            )
        }
    }

