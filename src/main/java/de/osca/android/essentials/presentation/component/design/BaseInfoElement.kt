package de.osca.android.essentials.presentation.component.design

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import de.osca.android.essentials.R

@Composable
fun BaseInfoElement(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    text: String = "",
    value: String = "",
    @DrawableRes icon: Int? = -1,
    @DrawableRes image: Int? = -1,
    imageUrl: String? = null,
    iconSize: Dp = 75.dp,
    @ColorRes iconTint: Int = -1,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
            if ((
                    moduleDesignArgs.mConstrainHeight
                        ?: masterDesignArgs.mConstrainHeight
                ) > 0.dp
            ) {
                Modifier
                    .height(moduleDesignArgs.mConstrainHeight ?: masterDesignArgs.mConstrainHeight)
                    .then(modifier)
            } else {
                Modifier
                    .then(modifier)
            },
    ) {
        Card(
            shape = RoundedCornerShape(moduleDesignArgs.mShapeCard ?: masterDesignArgs.mShapeCard),
            elevation = moduleDesignArgs.mCardElevation ?: masterDesignArgs.mCardElevation,
            backgroundColor =
                moduleDesignArgs.mButtonBackgroundColor
                    ?: masterDesignArgs.mCardBackColor,
            modifier =
                if (onClick != null) {
                    Modifier
                        .fillMaxSize()
                        .clickable {
                            onClick()
                        }
                } else {
                    Modifier
                        .fillMaxSize()
                },
        ) {
            Box(
                modifier =
                    Modifier
                        .padding(16.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                ) {
                    if (icon != null) {
                        Icon(
                            painter =
                                if (icon >= 0) {
                                    painterResource(id = icon)
                                } else if (imageUrl != null) {
                                    rememberAsyncImagePainter(imageUrl)
                                } else {
                                    painterResource(id = R.drawable.ic_circle)
                                },
                            tint =
                                if (iconTint >= 0) {
                                    colorResource(id = iconTint)
                                } else {
                                    (
                                        moduleDesignArgs.mCardTextColor
                                            ?: masterDesignArgs.mCardTextColor
                                    )
                                },
                            contentDescription = null,
                            modifier =
                                Modifier
                                    .padding(bottom = 20.dp, top = 10.dp)
                                    .height(iconSize)
                                    .width(iconSize),
                        )
                    } else if (image != null) {
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = null,
                            modifier =
                                Modifier
                                    .padding(bottom = 20.dp, top = 10.dp)
                                    .height(iconSize)
                                    .width(iconSize),
                        )
                    }

                    Text(
                        text = text,
                        style = masterDesignArgs.bodyTextStyle,
                        color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                        textAlign = TextAlign.Center,
                    )

                    if (value.isNotBlank()) {
                        Text(
                            text = value,
                            color =
                                moduleDesignArgs.mCardTextColor
                                    ?: masterDesignArgs.mCardTextColor,
                            style = masterDesignArgs.normalTextStyle,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}
