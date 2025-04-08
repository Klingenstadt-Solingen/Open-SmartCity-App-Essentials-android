package de.osca.android.essentials.domain.entity

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import de.osca.android.essentials.R

enum class CardinalDirection {
    N, NNE, NE, ENE, E, ESE, SE, SSE, S, SSW, SW, WSW, W, WNW, NW, NNW, UNKNOWN;

    fun simplified(): CardinalDirection {
        return when (this) {
            NNE, ENE -> NE
            ESE, SSE -> SE
            SSW, WSW -> SW
            WNW, NNW -> NW
            else -> this
        }
    }

    @Composable
    fun asText(): String {
        return when (this.simplified()) {
            N -> stringResource(id = R.string.global_direction_north)
            NE -> stringResource(id = R.string.global_direction_northeast)
            E -> stringResource(id = R.string.global_direction_east)
            SE -> stringResource(id = R.string.global_direction_southeast)
            S -> stringResource(id = R.string.global_direction_south)
            SW -> stringResource(id = R.string.global_direction_southwest)
            W -> stringResource(id = R.string.global_direction_west)
            NW -> stringResource(id = R.string.global_direction_northwest)
            else -> stringResource(id = R.string.global_direction_unknown)
        }
    }

    companion object {
        fun fromDegree(degree: Float): CardinalDirection? {
            if (degree > 360 || degree < 0) return UNKNOWN

            val targetIndex = when (degree) {
                in 0f..22.5f -> 0
                else -> (degree / 22.5).toInt() - 1
            }

            // Caution: For this list, items order matters (affects the result)
            val directions = listOf(
                N, NNE, NE, ENE, E, ESE, SE, SSE, S, SSW, SW, WSW, W, WNW, NW, NNW
            )

            return directions.getOrNull(targetIndex) ?: UNKNOWN
        }

        fun fromDegreeSimplified(degree: Float): CardinalDirection? {
            return fromDegree(degree)?.simplified() ?: UNKNOWN
        }
    }
}
