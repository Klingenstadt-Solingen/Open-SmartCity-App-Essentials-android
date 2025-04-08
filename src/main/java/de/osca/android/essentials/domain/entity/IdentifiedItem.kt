package de.osca.android.essentials.domain.entity

data class IdentifiedItem<I, V>(
    val id: I,
    val value: V
)