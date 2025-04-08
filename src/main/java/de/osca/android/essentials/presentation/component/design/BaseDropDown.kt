package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.osca.android.essentials.R

@Composable
fun BaseDropDown(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    displayTexts: List<String>,
    modifier: Modifier = Modifier.fillMaxWidth(),
    verticalPadding: Dp = 12.dp,
    horizontalPadding: Dp = 12.dp,
    reset: Boolean = false,
    initialItem: Int = 0,
    onSelectedItemChanged: ((index: Int) -> Unit)? = null,
) {
    val selectedIndex = remember { mutableIntStateOf(initialItem) }
    var expanded by remember { mutableStateOf(false) }

    if (reset) {
        selectedIndex.intValue = 0
    }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            Modifier
                .then(modifier),
    ) {
        Card(
            shape = RoundedCornerShape(moduleDesignArgs.mShapeCard ?: masterDesignArgs.mShapeCard),
            elevation = 0.dp,
            backgroundColor = moduleDesignArgs.mCardBackColor ?: masterDesignArgs.mCardBackColor,
            border =
                BorderStroke(
                    1.dp,
                    moduleDesignArgs.mDropDownBorderColor ?: masterDesignArgs.mDropDownBorderColor,
                ),
        ) {
            Row(
                Modifier
                    .padding(vertical = verticalPadding, horizontal = horizontalPadding)
                    .clickable {
                        expanded = !expanded
                    }.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (displayTexts.isNotEmpty()) {
                    if (onSelectedItemChanged != null) {
                        onSelectedItemChanged(selectedIndex.value)
                    }

                    Text(
                        text = displayTexts.getOrNull(selectedIndex.value) ?: "",
                        color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                        style = masterDesignArgs.normalTextStyle,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier =
                            Modifier
                                .weight(1f),
                    )
                } else {
                    Text(
                        text = stringResource(id = R.string.essentials_fetching_data),
                        color = moduleDesignArgs.mHintTextColor ?: masterDesignArgs.mHintTextColor,
                        style = masterDesignArgs.normalTextStyle,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier =
                            Modifier
                                .weight(1f),
                    )
                }

                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    tint = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                    contentDescription = "",
                    modifier =
                        Modifier
                            .weight(.1f),
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    },
                    modifier =
                        Modifier
                            .heightIn(max = 500.dp)
                            .background(
                                moduleDesignArgs.mCardBackColor ?: masterDesignArgs.mCardBackColor,
                            ),
                ) {
                    displayTexts.forEachIndexed { index, obj ->
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                selectedIndex.value = index

                                if (onSelectedItemChanged != null) {
                                    onSelectedItemChanged(selectedIndex.value)
                                }
                            },
                        ) {
                            Text(
                                text = obj,
                                color =
                                    moduleDesignArgs.mCardTextColor
                                        ?: masterDesignArgs.mCardTextColor,
                                style = masterDesignArgs.normalTextStyle,
                            )
                        }
                    }
                }
            }
        }
    }
}
