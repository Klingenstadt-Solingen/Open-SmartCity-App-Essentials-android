package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpecialLabelText(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = masterDesignArgs.normalTextStyle,
        color = moduleDesignArgs.mHintTextColor ?: masterDesignArgs.mHintTextColor,
        modifier = Modifier
            .border(
                1.dp,
                moduleDesignArgs.mHintTextColor ?: masterDesignArgs.mHintTextColor,
                RoundedCornerShape(moduleDesignArgs.mShapeCard ?: masterDesignArgs.mShapeCard)
            )
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .then(modifier)
    )
}