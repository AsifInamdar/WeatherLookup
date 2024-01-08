package com.asif.weatherlookup.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.asif.weatherlookup.R
import com.asif.weatherlookup.ui.theme.DegreeColor

@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {

        HomeHeaderCompose()

        WorldMapImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }

}

@Composable
fun WorldMapImage(modifier: Modifier) {

    ConstraintLayout(modifier = modifier.fillMaxWidth()) {

        val (map, weather) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.world_map),
            contentDescription = "map",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .alpha(0.4f)
                .constrainAs(map) {
                    top.linkTo(parent.top)
                }
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .constrainAs(weather) {
                    top.linkTo(map.top, margin = 80.dp)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GifImageCompose(modifier = Modifier.size(120.dp))

            Spacer(modifier = Modifier.size(30.dp))

            Text(
                text = "Thunder",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Row {
                Text(
                    text = "13",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 80.sp
                )
                DegreeView()
            }

        }
    }

}

@Composable
fun DegreeView() {
    Box(
        modifier = Modifier
            .padding(top = 25.dp, start = 5.dp)
            .size(20.dp)
            .border(width = 4.dp, color = DegreeColor, shape = CircleShape)
            .background(Color.Transparent)
    )
}

@Composable
fun HomeHeaderCompose() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Column {
            Text(text = "29, Dec 2023", color = Color.White, fontWeight = FontWeight.Light)
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(R.drawable.location_pin),
                    contentDescription = "location",
                    tint = Color.LightGray
                )
                val location = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append("Bangalore, ")
                    }

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Light)) {
                        append("India")
                    }
                }
                Text(
                    text = location,
                    modifier = Modifier.padding(start = 10.dp),
                    color = Color.White
                )
            }
        }
        UiModeCompose()
    }
}

@Composable
fun UiModeCompose() {
    Row(
        modifier = Modifier
            .border(
                width = 0.5.dp,
                shape = RoundedCornerShape(20.dp),
                color = Color.LightGray,
            )
            .padding(5.dp)
    ) {
        IconButton(onClick = { }, modifier = Modifier.size(30.dp)) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(R.drawable.light_mode_icon),
                contentDescription = "light mode",
                tint = Color.LightGray
            )
        }

        IconButton(
            onClick = { }, modifier = Modifier
                .size(30.dp)
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(R.drawable.dark_mode_icon),
                contentDescription = "dark mode",
                tint = Color.LightGray
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
