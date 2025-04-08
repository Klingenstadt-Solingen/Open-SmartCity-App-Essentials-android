package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ZoomableImage(
    imageUrl: String = "",
    moduleDesignArgs: ModuleDesignArgs,
    masterDesignArgs: MasterDesignArgs
) {
    val scale = remember { mutableStateOf(1f) }
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }
    val maxZoom = 5f

    Box(
        modifier = Modifier
            .clip(RectangleShape)
            .fillMaxSize()
            .background(moduleDesignArgs.mCardBackColor ?: masterDesignArgs.mCardBackColor)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale.value *= zoom
                    if (scale.value > maxZoom) scale.value = maxZoom
                    if (scale.value < 1f) scale.value = 1f

                    val maxX = (size.width * (scale.value - 1f)) / 2f
                    val minX = -maxX
                    offsetX.value = maxOf(minX, minOf(maxX, offsetX.value + pan.x))
                    val maxY = (size.height * (scale.value - 1f)) / 2f
                    val minY = -maxY
                    offsetY.value = maxOf(minY, minOf(maxY, offsetY.value + pan.y))
                }
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer(
                    scaleX = scale.value,
                    scaleY = scale.value,
                    translationX = offsetX.value,
                    translationY = offsetY.value
                ),
            contentDescription = null,
        )
    }
}