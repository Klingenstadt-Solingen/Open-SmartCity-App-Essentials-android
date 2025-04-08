package de.osca.android.essentials.presentation.component.design

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.osca.android.essentials.domain.entity.BaseListState

@Composable
fun BaseListContainer(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    text: String,
    textRowModifier: Modifier = Modifier.fillMaxWidth(),
    overrideSpace: Dp? = null,
    @DrawableRes iconUnderLine: Int = -1,
    underLineColor: Color = Color.White,
    initialState: BaseListState = BaseListState.Collapsed,
    showMoreOption: Boolean = masterDesignArgs.vShowMoreOption,
    onMoreOptionText: String? = null,
    onMoreOptionClick: (() -> Unit)? = null,
    contentsHeight: Dp = 1000.dp,
    contents: (LazyListScope.(MutableState<BaseListState>) -> Unit)? = null,
    content: @Composable (ColumnScope.(MutableState<BaseListState>) -> Unit)?,
) {
    val currentState = remember { mutableStateOf(initialState) }
    val isExpanded = currentState.value == BaseListState.Expanded

    fun switchState() {
        currentState.value =
            when (isExpanded) {
                true -> BaseListState.Collapsed
                false -> BaseListState.Expanded
            }
    }

    Column {
        if (iconUnderLine < 0) {
            BaseListHeader(
                title = text,
                parentListState = currentState.value,
                showMoreOption = showMoreOption,
                masterDesignArgs = masterDesignArgs,
                onMoreOptionText = onMoreOptionText,
                onMoreOptionClick =
                    onMoreOptionClick ?: {
                        switchState()
                    },
                moduleDesignArgs = moduleDesignArgs,
                rowModifier = textRowModifier,
            )
        } else {
            BaseListHeaderA(
                title = text,
                parentListState = currentState.value,
                iconUnderLine = iconUnderLine,
                underLineColor = underLineColor,
                showMoreOption = showMoreOption,
                masterDesignArgs = masterDesignArgs,
                onMoreOptionText = onMoreOptionText,
                onMoreOptionClick =
                    onMoreOptionClick ?: {
                        switchState()
                    },
                moduleDesignArgs = moduleDesignArgs,
                rowModifier = textRowModifier,
            )

            Spacer(modifier = Modifier.heightIn(4.dp))
        }

        Spacer(
            modifier =
                Modifier
                    .heightIn(8.dp),
        )

        if (content != null) {
            Column(
                verticalArrangement = Arrangement.spacedBy(overrideSpace ?: 16.dp),
            ) {
                content(currentState)
            }
        }

        if (contents != null) {
            LazyColumn(
                modifier = Modifier.height(contentsHeight),
                verticalArrangement = Arrangement.spacedBy(overrideSpace ?: 16.dp),
            ) {
                contents(currentState)
            }
        }
    }
}

@Composable
fun BaseListHeader(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    rowModifier: Modifier = Modifier.fillMaxWidth(),
    title: String,
    parentListState: BaseListState? = null,
    showMoreOption: Boolean = true,
    onMoreOptionText: String? = null,
    onMoreOptionClick: (() -> Unit)? = null,
) {
    val isExpanded = (parentListState == BaseListState.Expanded)

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = rowModifier,
    ) {
        Text(
            text = title,
            style = masterDesignArgs.overlineTextStyle,
            color = moduleDesignArgs.mHeaderTextColor ?: masterDesignArgs.mHeaderTextColor,
        )

        if (showMoreOption) {
            Text(
                text = onMoreOptionText ?:
                    when (isExpanded) {
                        true ->
                            stringResource(
                                id = moduleDesignArgs.mShowLessText ?: masterDesignArgs.mShowLessText,
                            )

                        false ->
                            stringResource(
                                id = moduleDesignArgs.mShowMoreText ?: masterDesignArgs.mShowMoreText,
                            )
                    },
                style = masterDesignArgs.normalTextStyle,
                textAlign = TextAlign.End,
                color = moduleDesignArgs.mShowMoreTextColor ?: masterDesignArgs.mShowMoreTextColor,
                modifier =
                    Modifier
                        .clickable {
                            onMoreOptionClick?.invoke()
                        },
            )
        }
    }
}

@Composable
fun BaseListHeaderA(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    rowModifier: Modifier = Modifier.fillMaxWidth(),
    title: String,
    @DrawableRes iconUnderLine: Int = -1,
    underLineColor: Color,
    parentListState: BaseListState? = null,
    showMoreOption: Boolean = true,
    onMoreOptionText: String? = null,
    onMoreOptionClick: (() -> Unit)? = null,
) {
    val isExpanded = (parentListState == BaseListState.Expanded)

    Column {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom,
            modifier = rowModifier,
        ) {
            Text(
                text = title,
                style = masterDesignArgs.overlineTextStyle,
                color = moduleDesignArgs.mHeaderTextColor ?: masterDesignArgs.mHeaderTextColor,
            )

            if (showMoreOption) {
                Spacer(modifier = Modifier.widthIn(16.dp))

                Text(
                    text = onMoreOptionText ?:
                        when (isExpanded) {
                            true ->
                                stringResource(
                                    id =
                                        moduleDesignArgs.mShowLessText
                                            ?: masterDesignArgs.mShowLessText,
                                )

                            false ->
                                stringResource(
                                    id =
                                        moduleDesignArgs.mShowMoreText
                                            ?: masterDesignArgs.mShowMoreText,
                                )
                        },
                    style = masterDesignArgs.normalTextStyle,
                    textAlign = TextAlign.End,
                    color =
                        moduleDesignArgs.mShowMoreTextColor
                            ?: masterDesignArgs.mShowMoreTextColor,
                    modifier =
                        Modifier
                            .clickable {
                                onMoreOptionClick?.invoke()
                            },
                )
            }
        }

        Spacer(modifier = Modifier.heightIn(4.dp))

        Icon(
            painter = painterResource(id = iconUnderLine),
            contentDescription = "underlineColored",
            tint = underLineColor,
            modifier =
                Modifier
                    .widthIn(73.dp)
                    .height(5.dp),
        )
    }
}
