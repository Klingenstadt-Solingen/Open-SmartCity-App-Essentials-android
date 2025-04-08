package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RootContainer(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    scrollState: LazyListState? = null,
    headerContent: (@Composable () -> Unit)? = null,
    initialOffset: Int = 0,
    onScroll: (Int) -> Unit = { },
    items: (LazyListScope.() -> Unit)
) {
    Column {
        if(headerContent != null) {
            headerContent()
        }

        LazyColumn(
            state = scrollState ?: LazyListState(firstVisibleItemScrollOffset = initialOffset),
            contentPadding = PaddingValues(moduleDesignArgs.mRootBoarderSpacing ?: masterDesignArgs.mBorderSpace),
            verticalArrangement = Arrangement.spacedBy(moduleDesignArgs.mRootCardSpacing ?: masterDesignArgs.spaceList),
            content = items
        )
    }
}

@Composable
fun RootContainer_Modified(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    overrideSpace: Dp? = null,
    scrollState: LazyListState? = null,
    headerContent: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    LazyColumn(
        state = scrollState ?: LazyListState(),
        verticalArrangement = Arrangement.spacedBy(moduleDesignArgs.mRootCardSpacing ?: masterDesignArgs.spaceList)
    ) {
        if(headerContent != null) {
            item {
                headerContent()
            }
        }

        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(overrideSpace ?: moduleDesignArgs.mRootCardSpacing ?: masterDesignArgs.mRootCardSpacing),
                modifier = Modifier
                    .padding(moduleDesignArgs.mRootBoarderSpacing ?: masterDesignArgs.mBorderSpace)
            ) {
                content()
            }
        }
    }
}