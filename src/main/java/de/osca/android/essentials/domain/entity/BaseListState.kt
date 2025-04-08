package de.osca.android.essentials.domain.entity

enum class BaseListState {
    Expanded,
    Collapsed;

    fun isExpanded(): Boolean {
        return this == Expanded
    }
}