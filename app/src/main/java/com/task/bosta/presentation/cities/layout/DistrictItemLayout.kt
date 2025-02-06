package com.task.bosta.presentation.cities.layout

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.task.bosta.data.model.DistrictModel


@Composable
fun DistrictItemLayout(district: DistrictModel) {
    val context = LocalContext.current
    Box(modifier = Modifier.background(color = Color(0xFFF2F4F7))) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable(enabled = !district.pickupAvailability) {
                    Toast.makeText(context, "You selected ${district.districtName}", Toast.LENGTH_SHORT).show()
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(district.districtName, style = MaterialTheme.typography.bodyMedium)
            if (district.pickupAvailability) {
                Box(
                    modifier = Modifier
                        .background(color = Color.White, shape = CircleShape)
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    Text(
                        "Uncovered",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.DarkGray)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DistrictItemLayoutPreview() {
    DistrictItemLayout(district = dataSample)
}

private val dataSample: DistrictModel = DistrictModel(districtName = "Abu Yousef", pickupAvailability = true)