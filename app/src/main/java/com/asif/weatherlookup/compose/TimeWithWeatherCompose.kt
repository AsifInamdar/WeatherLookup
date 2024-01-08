package com.asif.weatherlookup.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.asif.weatherlookup.R

@Composable
fun TimeWithWeatherCompose(){
    Column {
        Image(painter = painterResource(R.drawable.ic_raining), contentDescription = "weather icon")

        Text(text = "1:00 PM", color = Color.White)

        Row {
            Text(text = "11", color = Color.White)
        }
    }
}

@Composable
@Preview
fun TimeWithWeatherPreview(){
    TimeWithWeatherCompose()
}