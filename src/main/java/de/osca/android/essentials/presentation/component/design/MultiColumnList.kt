package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MultiColumnList(
    columnCount: Int = 1,
    useDummies: Boolean = false,
    contentList: () -> List<(@Composable (modifier: Modifier) -> Unit)?>
) {
    val contentRows = contentList().chunked(columnCount).toMutableList()

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        contentRows.forEach { contentRow ->
            val mutableContentRow = contentRow.toMutableList()
            if(useDummies && mutableContentRow.size < columnCount) {
                val toAddCount = columnCount - mutableContentRow.size
                val dummyList = mutableListOf<(@Composable (modifier: Modifier) -> Unit)?>()
                repeat(toAddCount) {
                    dummyList.add(null)
                }
                mutableContentRow.addAll(dummyList)
            }
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                mutableContentRow.forEach { content ->
                    if(content != null) {
                        content(Modifier.weight(1.0f / columnCount))
                    } else {
                        Box(modifier = Modifier.weight(1.0f / columnCount))
                    }
                }
            }
        }
    }
}