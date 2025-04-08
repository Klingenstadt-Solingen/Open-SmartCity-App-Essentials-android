package de.osca.android.essentials.presentation.component.text

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.osca.android.essentials.R
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs

@Composable
fun SearchTextFieldDecorationBox(
    innerTextField: @Composable () -> Unit,
    searchTextState: MutableState<String>,
    onClear: (() -> Unit)? = null,
    masterDesignArgs: MasterDesignArgs,
    overrideForegroundColor: Color? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                masterDesignArgs.mHintTextColor.copy(alpha = 0.4f),
                RoundedCornerShape(12.dp)
            )
            .padding(vertical = 6.dp, horizontal = 8.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = stringResource(id = R.string.global_search),
            tint = overrideForegroundColor ?: masterDesignArgs.mCardTextColor,
            modifier = Modifier
                .width(28.dp)
                .height(28.dp)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 6.dp),
        ) {
            innerTextField()
        }

        if (searchTextState.value.isNotBlank()) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close_circle),
                contentDescription = stringResource(id = R.string.global_search),
                tint = overrideForegroundColor ?: masterDesignArgs.mCardTextColor,
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
                    .clickable {
                        searchTextState.value = ""
                        onClear?.invoke()
                    }
            )
        }
    }
}