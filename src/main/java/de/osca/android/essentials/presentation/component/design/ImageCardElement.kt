package de.osca.android.essentials.presentation.component.design

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import de.osca.android.essentials.R

@Composable
fun ImageCardElement(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    titleText: String?,
    descriptionText: String?,
    useImagePadding: Boolean = false,
    cardHeight: Dp,
    @DrawableRes imageId: Int = -1,
    imageUrl: String? = "",
    constraintWidth: Dp? = null,
    externalLinkIconColorOverride: Color? = null,
    externalLinkTextColorOverride: Color? = null,
    onClickCard: (() -> Unit)? = null,
    isExternalLink: Boolean = false,
    modifier: Modifier = Modifier
) {
    val cornerSize = moduleDesignArgs.mShapeCard ?: masterDesignArgs.mShapeCard
    val elevation = moduleDesignArgs.mCardElevation ?: masterDesignArgs.mCardElevation
    Card(
        shape = RoundedCornerShape(cornerSize),
        elevation = elevation,
        backgroundColor = moduleDesignArgs.mCardBackColor ?: masterDesignArgs.mCardBackColor,
        modifier = modifier
            .then(
                if (constraintWidth != null) {
                    modifier
                        .requiredWidth(constraintWidth)
                } else Modifier
            )
            .requiredHeight(cardHeight)
            .advancedShadow(cornersRadius = cornerSize, elevation = elevation)
            .clip(RoundedCornerShape(cornerSize))
            .clickable(enabled = onClickCard != null) {
                onClickCard?.invoke()
            }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
        ) {
            Image(
                painter = if (imageId >= 0)
                    painterResource(id = imageId)
                else
                    rememberAsyncImagePainter(imageUrl),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (useImagePadding) Modifier
                            .padding(10.dp)
                            .clip(RoundedCornerShape(cornerSize))
                        else Modifier
                    )
                    .weight(2.4f),
                alignment = Alignment.TopCenter
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = if (useImagePadding)
                    Modifier
                        .weight(1.3f)
                        .padding(bottom = 10.dp)
                        .padding(horizontal = 10.dp)
                else
                    Modifier
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                        .weight(1.1f)
            ) {
                Column(modifier = Modifier
                    .weight(1f)
                ) {
                    Text(
                        text = titleText ?: "",
                        style = masterDesignArgs.bodyTextStyle,
                        color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                        modifier = Modifier
                            .padding(bottom = 4.dp),
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = descriptionText ?: "",
                        style = masterDesignArgs.normalTextStyle,
                        color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                        modifier = Modifier
                            .padding(bottom = 4.dp),
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if(isExternalLink) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_external_link),
                            contentDescription = "isExternalLink",
                            tint = externalLinkIconColorOverride ?: masterDesignArgs.mDialogsBackColor,
                            modifier = Modifier
                                .size(25.dp)
                        )
                        Text(
                            text = "extern",
                            style = masterDesignArgs.subtitleTextStyle,
                            color = externalLinkTextColorOverride ?: masterDesignArgs.mHintTextColor,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}