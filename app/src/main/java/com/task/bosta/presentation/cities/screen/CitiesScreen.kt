package com.task.bosta.presentation.cities.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.task.bosta.data.model.CityModel
import com.task.bosta.presentation.cities.layout.MenuLayout
import com.task.bosta.presentation.cities.viewModel.CitiesViewModel
import com.task.bosta.utils.network.State

@Composable
fun CitiesScreen() {
    val viewModel: CitiesViewModel = hiltViewModel()
    val state by viewModel.state.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCities()
    }

    when (state) {
        is State.Loading -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }

        is State.Success -> {
            val cities = (state as State.Success<List<CityModel>>).response.data
            MenuLayout(cities = cities) // Show the list of cities
        }

        is State.Error -> {
            val errorMessage = (state as State.Error).message
            Text(text = "Error: $errorMessage") // Show error message
        }

        null -> {}
    }
}

@Preview(showBackground = true)
@Composable
private fun CitiesScreenPreview() {
    CitiesScreen()
}