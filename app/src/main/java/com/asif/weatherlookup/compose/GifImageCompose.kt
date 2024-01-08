package com.asif.weatherlookup.compose

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.asif.weatherlookup.R

@Composable
fun GifImageCompose(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .placeholder(R.drawable.lightning)
        .build()


    Image(
        painter = rememberAsyncImagePainter(R.drawable.lightning, imageLoader),
        contentDescription = "gif",
        modifier = modifier
    )
}

@Preview
@Composable
fun GifImageComposePreview() {
    GifImageCompose()
}