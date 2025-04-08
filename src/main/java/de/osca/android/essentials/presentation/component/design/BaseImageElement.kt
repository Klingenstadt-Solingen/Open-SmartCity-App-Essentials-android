package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalCoilApi::class)
@Composable
fun BaseImageElement(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    modifier: Modifier,
    imageUrl: String = "",
    onClick: () -> Unit
) {
    BaseCardContainer(
        masterDesignArgs = masterDesignArgs,
        moduleDesignArgs = moduleDesignArgs,
        useContentPadding = false,
        overrideConstraintHeight = 150.dp,
        modifier = modifier,
        onClick = onClick
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}