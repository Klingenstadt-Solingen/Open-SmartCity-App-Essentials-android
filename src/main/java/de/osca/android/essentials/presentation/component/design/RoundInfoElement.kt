package de.osca.android.essentials.presentation.component.design

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.material.Icon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun RoundInfoElement(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    text: String? = null,
    @DrawableRes icon: Int = -1,
    iconUrl: String? = null,
    color: Color? = null,
    onClick: (() -> Unit)? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = if ((moduleDesignArgs.mConstrainHeight ?: masterDesignArgs.mConstrainHeight) > 0.dp)
            Modifier
                .padding(bottom = 24.dp)
                .fillMaxHeight()
                .widthIn(moduleDesignArgs.mRoundIconSize ?: masterDesignArgs.mRoundIconSize)
                .height(moduleDesignArgs.mConstrainHeight ?: masterDesignArgs.mConstrainHeight)
        else
            Modifier
                .padding(bottom = 24.dp)
                .fillMaxHeight()
                .widthIn(moduleDesignArgs.mRoundIconSize ?: masterDesignArgs.mRoundIconSize)
    ) {
        Card(
            shape = RoundedCornerShape(50),
            elevation = moduleDesignArgs.mCardElevation ?: masterDesignArgs.mCardElevation,
            backgroundColor = masterDesignArgs.mCardBackColor,
            modifier =
            if (onClick != null)
                Modifier
                    .padding(bottom = 20.dp)
                    .size((moduleDesignArgs.mRoundIconSize ?: masterDesignArgs.mRoundIconSize) - 20.dp)
                    .clip(RoundedCornerShape(50))
                    .clickable {
                        onClick()
                    }
            else
                Modifier
                    .padding(bottom = 20.dp)
                    .size((moduleDesignArgs.mRoundIconSize ?: masterDesignArgs.mRoundIconSize) - 20.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(color ?: masterDesignArgs.mCardBackColor, RoundedCornerShape(50))
            )
            if(iconUrl != null){
                AsyncImage(iconUrl, text, modifier = Modifier
                    .padding(10.dp), contentScale = ContentScale.FillBounds)
            } else {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }

        }

        if(text != null) {
            Text(
                text = text,
                style = masterDesignArgs.normalTextStyle,
                color = moduleDesignArgs.mScreenTextColor ?: masterDesignArgs.mScreenTextColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .widthIn(
                        min = 25.dp,
                        max = (moduleDesignArgs.mRoundIconSize ?: masterDesignArgs.mRoundIconSize) - 5.dp
                    )
            )
        }
    }
}
