package com.task.bosta.presentation.cities.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.task.bosta.data.model.CityModel
import com.task.bosta.data.model.DistrictModel

@Composable
fun MenuLayout(cities: List<CityModel>) {
    var searchText by remember { mutableStateOf("") }

    val filteredCities = cities.filter {
        it.cityName.contains(searchText, ignoreCase = true) ||
                it.districts.any { district -> district.districtName.contains(searchText, ignoreCase = true) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text("Choose the delivery area", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        // Search Bar
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("City/District") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        // List of Cities
        LazyColumn {
            items(filteredCities.size) { index ->
                CityItemLayout(filteredCities[index])
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuLayoutPreview() {
    MenuLayout(cities = dataSample)
}

private val dataSample: List<CityModel> = listOf(
    CityModel(
        cityName = "Alexandria",
        districts = listOf(
            DistrictModel(districtName = "Abu Yousef", true)
        ),
    ), CityModel(
        cityName = "Giza",
        districts = listOf(
            DistrictModel(districtName = "6th of October", false)
        ),
    )
)