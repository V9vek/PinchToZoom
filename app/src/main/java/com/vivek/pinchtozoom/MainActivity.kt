package com.vivek.pinchtozoom

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mxalbert.zoomable.OverZoomConfig
import com.mxalbert.zoomable.Zoomable
import com.mxalbert.zoomable.rememberZoomableState
import com.vivek.pinchtozoom.ui.components.CustomTopAppBar
import com.vivek.pinchtozoom.ui.theme.PinchToZoomTheme
import com.vivek.pinchtozoom.util.loadImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            // Update the system bars to be translucent
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setSystemBarsColor(Color.White.copy(0.2f))
            }

            PinchToZoomTheme {
                ProvideWindowInsets {
                    Sample()
                }
            }
        }
    }
}

@Composable
fun Sample() {
    // for showing zooming is happening or not
    var isZooming by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { CustomTopAppBar(isZooming = isZooming) }
    ) { contentPadding ->
        LazyColumn(
            contentPadding = contentPadding,
            modifier = Modifier
                .fillMaxSize()
                .animateContentSize()
        ) {
            items(items = provideList()) { img ->
                val image = loadImage(url = img)
                ZoomableItem(
                    image = image,
                    isZoomingToggled = { isZooming = it }
                )
            }
        }
    }
}


@Composable
fun ZoomableItem(
    image: Bitmap?,
    isZoomingToggled: (Boolean) -> Unit
) {
    val state = rememberZoomableState(
        overZoomConfig = OverZoomConfig(minSnapScale = 1f, maxSnapScale = 1f)
    )

    isZoomingToggled(state.isZooming)

    Zoomable(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(8.dp)
            .zIndex(if (state.isZooming) 1f else 0f)
    ) {
        image?.let { img ->
            Image(
                bitmap = img.asImageBitmap(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )
        }
    }
}

private fun provideList() = listOf(
    "https://images7.alphacoders.com/101/thumb-1920-1010687.jpg",
    "https://images.alphacoders.com/718/thumb-1920-718631.png",
    "https://images3.alphacoders.com/905/thumb-1920-905276.jpg",
    "https://images.alphacoders.com/104/thumb-1920-1049984.jpg",
    "https://images3.alphacoders.com/644/thumb-1920-644196.jpg",
    "https://images8.alphacoders.com/840/thumb-1920-840020.png",
    "https://images3.alphacoders.com/135/thumb-1920-135625.jpg",
    "https://images7.alphacoders.com/652/thumb-1920-652552.jpg",
    "https://images2.alphacoders.com/747/thumb-1920-747570.png",
    "https://images7.alphacoders.com/607/thumb-1920-607718.png",
)

















