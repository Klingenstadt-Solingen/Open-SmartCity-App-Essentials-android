package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BaseCardContainer(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    useContentPadding: Boolean = true,
    overrideConstraintHeight: Dp? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String = "",
    isTitleBig: Boolean = false,
    isTitleCentered: Boolean = false,
    useBigHeaderSpace: Boolean = true,
    isRound: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Column(
        horizontalAlignment = if(isTitleCentered) Alignment.CenterHorizontally else Alignment.Start,
        modifier = modifier
    ) {
        if(!(moduleDesignArgs.mTextInCard ?: masterDesignArgs.mTextInCard) && text.isNotBlank()) {
            Text(
                text = text,
                style = if(isTitleBig) masterDesignArgs.captionTextStyle else masterDesignArgs.overlineTextStyle,
                color = moduleDesignArgs.mScreenTextColor ?: masterDesignArgs.mScreenTextColor,
                modifier = if(isTitleBig && useBigHeaderSpace)
                    Modifier
                        .padding(bottom = 20.dp, top = 32.dp)
                        .padding(horizontal = 5.dp)
                else
                    Modifier
                        .padding(bottom = 20.dp)
                        .padding(horizontal = 5.dp)
            )
        }

        val cornerSize = if(!isRound) moduleDesignArgs.mShapeCard ?: masterDesignArgs.mShapeCard else overrideConstraintHeight?.div(2.dp)?.dp ?: 0.dp
        val elevation = moduleDesignArgs.mCardElevation ?: masterDesignArgs.mCardElevation
        Card(
            shape = if(!isRound) RoundedCornerShape(moduleDesignArgs.mShapeCard ?: masterDesignArgs.mShapeCard) else RoundedCornerShape(50),
            backgroundColor = moduleDesignArgs.mCardBackColor ?: masterDesignArgs.mCardBackColor,
            elevation = elevation,
            modifier = if((overrideConstraintHeight ?: moduleDesignArgs.mConstrainHeight ?: masterDesignArgs.mConstrainHeight) > 0.dp)
                if(onClick != null) {
                    Modifier
                        .fillMaxWidth()
                        .height(
                            overrideConstraintHeight ?: moduleDesignArgs.mConstrainHeight
                            ?: masterDesignArgs.mConstrainHeight
                        )
                        .advancedShadow(cornersRadius = cornerSize, elevation = elevation)
                        .clip(if(!isRound) RoundedCornerShape(masterDesignArgs.mShapeCard) else RoundedCornerShape(50))
                        .clickable {
                            onClick()
                        }
                } else {
                    Modifier
                        .fillMaxWidth()
                        .advancedShadow(cornersRadius = cornerSize, elevation = elevation)
                        .height(
                            overrideConstraintHeight ?: moduleDesignArgs.mConstrainHeight
                            ?: masterDesignArgs.mConstrainHeight
                        )
                }
            else
                if(onClick != null) {
                    Modifier
                        .fillMaxWidth()
                        .advancedShadow(cornersRadius = cornerSize, elevation = elevation)
                        .clip(if(!isRound) RoundedCornerShape(masterDesignArgs.mShapeCard) else RoundedCornerShape(50))
                        .clickable {
                            onClick()
                        }
                } else {
                    Modifier
                        .fillMaxWidth()
                        .advancedShadow(cornersRadius = cornerSize, elevation = elevation)
                }
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(if(useContentPadding) (moduleDesignArgs.mCardContentPadding ?: masterDesignArgs.mCardContentPadding) else 0.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                ) {
                    if ((moduleDesignArgs.mTextInCard ?: masterDesignArgs.mTextInCard) && text.isNotBlank()) {
                        Text(
                            text = text,
                            style = masterDesignArgs.bodyTextStyle,
                            color = masterDesignArgs.mCardTextColor
                        )

                        Spacer(modifier = Modifier
                            .height(if((moduleDesignArgs.mCardContentPadding ?: masterDesignArgs.mCardContentPadding) > 0.dp) 10.dp else 0.dp)
                        )
                    }

                    Box {
                        content()
                    }
                }
            }
        }
    }
}