package de.osca.android.essentials.utils.booleanExtension

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import de.osca.android.essentials.R
import de.osca.android.essentials.domain.entity.BooleanLiteralType

@Composable
fun Boolean.toLiteral(booleanLiteralType: BooleanLiteralType): String {
    return when (booleanLiteralType) {
        BooleanLiteralType.YES_NO -> if (this) stringResource(R.string.global_yes) else stringResource(R.string.global_no)
        BooleanLiteralType.ON_OFF ->if (this) stringResource(R.string.global_on) else stringResource(R.string.global_off)
    }
}

fun Boolean.toLiteral(context: Context, literalType: BooleanLiteralType): String {
    return when (literalType) {
        BooleanLiteralType.YES_NO -> if (this) context.getString(R.string.global_yes) else context.getString(R.string.global_no)
        BooleanLiteralType.ON_OFF ->if (this) context.getString(R.string.global_on) else context.getString(R.string.global_off)
    }
}