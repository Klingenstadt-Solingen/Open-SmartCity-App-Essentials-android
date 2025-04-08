package de.osca.android.essentials.presentation.component.design

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HeaderImageCard(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    overrideHeaderImageHeight: Dp? = null,
    @DrawableRes image: Int,
    imageDescription: String? = "",
    modifier: Modifier = Modifier,
    spaceToTop: Dp = 50.dp,
    onClick: (() -> Unit)? = null,
    content: (@Composable () -> Unit)? = null
) {
    Column(modifier = Modifier
        .padding(top = spaceToTop)
        .then(modifier)
    ) {
        BaseCardContainer(
            moduleDesignArgs = moduleDesignArgs,
            useContentPadding = false,
            onClick = onClick,
            masterDesignArgs = masterDesignArgs
        ) {
            if(image >= 0) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = imageDescription,
                    contentScale = ContentScale.Crop,
                    modifier = if(overrideHeaderImageHeight != null)
                        Modifier
                            .fillMaxSize()
                            .height(overrideHeaderImageHeight)
                    else
                        Modifier
                            .fillMaxSize()

                )
            }

            if (content != null) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                ) {
                    content()
                }
            }
        }
    }
}