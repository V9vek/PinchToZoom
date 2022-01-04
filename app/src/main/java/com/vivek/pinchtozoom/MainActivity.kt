package com.vivek.pinchtozoom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.mxalbert.zoomable.rememberZoomableState
import com.vivek.pinchtozoom.ui.theme.PinchToZoomTheme
import com.vivek.pinchtozoom.util.loadImage
import com.vivek.pinchtozoom.zoomable.Zoomable
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val dummyList = (1..5).toList()
            var currentItemSelected by remember { mutableStateOf(0) }

            PinchToZoomTheme {
                Surface(color = MaterialTheme.colors.background) {
                    LazyColumn {
                        itemsIndexed(items = dummyList) { index, item ->
                            MainCompose(
                                index = index,
                                onSelectItem = { currentItemSelected = it },
                                currentItemSelected = currentItemSelected
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainCompose(
    index: Int,
    currentItemSelected: Int,
    onSelectItem: (Int) -> Unit,
) {
    val image = loadImage(image = R.drawable.pic)
    val state = rememberZoomableState()

    Zoomable(
        selectedItem = index,
        onSelectItem = onSelectItem,
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(8.dp)
            .zIndex(if (index == currentItemSelected) 1f else 0f)
    ) {
        image?.let { img ->
            Image(
                bitmap = img.asImageBitmap(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio((img.width / img.height).toFloat())
                    .zIndex(1f)
            )
        }
    }
}

























