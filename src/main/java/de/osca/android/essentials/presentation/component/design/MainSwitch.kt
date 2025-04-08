package de.osca.android.essentials.presentation.component.design

import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun MainSwitch(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    initialState: Boolean = false,
    onSwitch: (checked: Boolean) -> Unit
) {
    val checkedState = remember { mutableStateOf(initialState) }

    Switch(
        checked = checkedState.value,
        colors = if(moduleDesignArgs.mSwitchCheckedThumbColor != null) moduleDesignArgs.getSwitchColors() else masterDesignArgs.getSwitchColors(),
        onCheckedChange = {
            checkedState.value = it

            onSwitch(checkedState.value)
        }
    )
}