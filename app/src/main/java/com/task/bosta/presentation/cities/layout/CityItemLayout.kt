package com.task.bosta.presentation.cities.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.task.bosta.data.model.CityModel
import com.task.bosta.data.model.DistrictModel

@Composable
fun CityItemLayout(city: CityModel) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(city.cityName, style = MaterialTheme.typography.bodySmall)
            Icon(
                if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown, contentDescription = "Expand/Collapse Icon"
            )
        }

        if (isExpanded) {
            Column {
                city.districts.forEach { area ->
                    DistrictItemLayout(area)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CityItemLayoutPreview() {
    CityItemLayout(city = dataSample)
}

private val dataSample: CityModel = CityModel(
    cityName = "Alexandria", districts = listOf(
        DistrictModel(districtName = "Abu Yousef", true)
    )
)