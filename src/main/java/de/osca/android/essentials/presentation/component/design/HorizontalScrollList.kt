package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalScrollList(
    space: Dp = 40.dp,
    borderSpace: Dp = 16.dp,
    scrollState: LazyListState? = null,
    modifier: Modifier = Modifier,
    items: (LazyListScope.() -> Unit)
) {
    LazyRow(
        state = scrollState ?: LazyListState(),
        content = items,
        contentPadding = PaddingValues(borderSpace),
        horizontalArrangement = Arrangement.spacedBy(space),
        modifier = modifier
    )
}