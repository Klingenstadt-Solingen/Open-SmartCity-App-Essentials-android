package de.osca.android.essentials.presentation.component.design

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import de.osca.android.essentials.domain.entity.IdentifiedItem
import de.osca.android.essentials.presentation.component.text.SearchTextFieldDecorationBox
import de.osca.android.essentials.utils.extensions.resetWith
import java.util.Collections.emptyList

@Composable
fun BaseTextField(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    overrideForegroundColor: Color? = null,
    textFieldTitle: String,
    textValue: MutableState<String>? = null,
    isError: Boolean = false,
    isOutlined: Boolean = true,
    lineCount: Int = 1,
    fieldHeight: Dp = 0.dp,
    isPasswordField: Boolean? = false,
    suggestions: List<IdentifiedItem<String, String>> = emptyList(),
    isSuggestionCaseSensitive: Boolean = false,
    onOptionSelected: ((identifier: String?, value: String) -> Unit)? = null,
    onTextChange: (text: String) -> Unit,
    onClear: (() -> Unit)? = null,
    onDone: (KeyboardActionScope.() -> Unit)? = null,
    enabled: Boolean = true,
    focusRequester: FocusRequester = FocusRequester(),
    keyboardOptions: KeyboardOptions? = null,
) {
    IdentifiedTextField(
        textFieldTitle = textFieldTitle,
        textValue = textValue,
        isError = isError,
        isOutlined = isOutlined,
        lineCount = lineCount,
        fieldHeight = fieldHeight,
        suggestions = suggestions,
        isPasswordField = isPasswordField ?: false,
        isSuggestionCaseSensitive = isSuggestionCaseSensitive,
        onOptionSelected = onOptionSelected,
        onTextChange = onTextChange,
        onClear = onClear,
        onDone = onDone,
        masterDesignArgs = masterDesignArgs,
        moduleDesignArgs = moduleDesignArgs,
        overrideForegroundColor = overrideForegroundColor,
        enabled = enabled,
        focusRequester = focusRequester,
        keyboardOptions = keyboardOptions,
    )
}

/***
 * IdentifiedTextField allows to set identified suggestions to the field
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <T> IdentifiedTextField(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    overrideForegroundColor: Color? = null,
    textFieldTitle: String,
    textValue: MutableState<String>? = null,
    isError: Boolean = false,
    isOutlined: Boolean = true,
    lineCount: Int = 1,
    fieldHeight: Dp = 0.dp,
    isPasswordField: Boolean = false,
    suggestions: List<IdentifiedItem<T, String>> = emptyList(),
    isSuggestionCaseSensitive: Boolean = false,
    onOptionSelected: ((identifier: T?, value: String) -> Unit)? = null,
    onTextChange: ((text: String) -> Unit)?,
    onClear: (() -> Unit)? = null,
    onDone: (KeyboardActionScope.() -> Unit)? = null,
    enabled: Boolean = true,
    focusRequester: FocusRequester = FocusRequester(),
    keyboardOptions: KeyboardOptions? = null,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val name = textValue ?: remember { mutableStateOf("") }
    val expanded = remember { mutableStateOf(false) }
    val displayedSuggestions = remember { mutableStateListOf<IdentifiedItem<T, String>>() }

    val colorState =
        animateColorAsState(
            when (isError) {
                true -> masterDesignArgs.errorTextColor
                false -> masterDesignArgs.mTextFieldLabelColor
            },
        )

    val showPassword =
        remember {
            mutableStateOf(isPasswordField)
        }

    if (isOutlined) {
        Column {
            OutlinedTextField(
                value = name.value,
                enabled = enabled,
                visualTransformation = if (showPassword.value) PasswordVisualTransformation() else VisualTransformation.None,
                modifier =
                    if (fieldHeight > 0.dp) {
                        Modifier
                            .fillMaxWidth()
                            .height(fieldHeight)
                            .focusRequester(focusRequester)
                    } else {
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .focusRequester(focusRequester)
                    },
                onValueChange = { currentText ->
                    name.value = currentText

                    if (name.value.isNotEmpty()) {
                        expanded.value = true

                        val newSuggestions =
                            suggestions.filter {
                                it.value.contains(name.value, ignoreCase = !isSuggestionCaseSensitive)
                            }

                        displayedSuggestions.resetWith(newSuggestions)
                    } else {
                        expanded.value = false
                    }

                    onTextChange?.invoke(name.value)
                },
                textStyle =
                    masterDesignArgs.normalTextStyle.copy(
                        color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                    ),
                colors = if (moduleDesignArgs.mTextFieldFocusedBorderColor != null) moduleDesignArgs.getTextFieldColors() else masterDesignArgs.getTextFieldColors(),
                shape =
                    RoundedCornerShape(
                        moduleDesignArgs.mShapeCard ?: masterDesignArgs.mShapeCard,
                    ),
                label = {
                    Text(
                        text = textFieldTitle,
                        color = colorState.value,
                        style = masterDesignArgs.normalTextStyle,
                    )
                },
                keyboardOptions = keyboardOptions ?: KeyboardOptions.Default,
                keyboardActions =
                    KeyboardActions(
                        onDone = onDone,
                    ),
                maxLines = if (fieldHeight > 0.dp) 100 else lineCount,
                singleLine = lineCount == 1 && fieldHeight <= 0.dp,
                isError = isError,
                trailingIcon = {
                    if (isPasswordField) {
                        val image =
                            if (showPassword.value) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            }

                        val description =
                            if (showPassword.value) "verstecke Passwort" else "zeige Passwort"

                        IconButton(
                            onClick = {
                                showPassword.value = !showPassword.value
                            },
                        ) {
                            Icon(
                                imageVector = image,
                                tint =
                                    moduleDesignArgs.mTextFieldFocusedBorderColor
                                        ?: masterDesignArgs.mTextFieldFocusedBorderColor,
                                contentDescription = description,
                            )
                        }
                    }
                },
            )

            if (displayedSuggestions.isNotEmpty() && suggestions.isNotEmpty() && expanded.value) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .padding(top = 16.dp),
                ) {
                    displayedSuggestions.forEach { identifiedString ->
                        item {
                            Text(
                                text = identifiedString.value,
                                style = masterDesignArgs.bodyTextStyle,
                                color =
                                    moduleDesignArgs.mMenuTextColor
                                        ?: masterDesignArgs.mMenuTextColor,
                                modifier =
                                    Modifier
                                        .clickable {
                                            expanded.value = false
                                            name.value = identifiedString.value

                                            if (onOptionSelected != null) {
                                                onOptionSelected(
                                                    identifiedString.id,
                                                    identifiedString.value,
                                                )
                                            }

                                            focusManager.clearFocus()
                                            keyboardController?.hide()
                                        },
                            )
                        }
                    }
                }
            }

            if (false && displayedSuggestions.isNotEmpty() && suggestions.isNotEmpty() && expanded.value) {
                DropdownMenu(
                    expanded = suggestions.isNotEmpty(),
                    onDismissRequest = {
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth(.75f)
                            .heightIn(max = 350.dp)
                            .background(
                                moduleDesignArgs.mMenuBackColor ?: masterDesignArgs.mMenuBackColor,
                            ),
                ) {
                    displayedSuggestions.forEach { identifiedString ->
                        DropdownMenuItem(
                            onClick = {
                                name.value = identifiedString.value
                                expanded.value = false

                                if (onOptionSelected != null) {
                                    onOptionSelected(identifiedString.id, identifiedString.value)
                                }

                                focusManager.clearFocus()
                                keyboardController?.hide()
                            },
                        ) {
                            Text(
                                text = identifiedString.value,
                                style = masterDesignArgs.normalTextStyle,
                                color =
                                    moduleDesignArgs.mMenuTextColor
                                        ?: masterDesignArgs.mMenuTextColor,
                            )
                        }
                    }
                }
            }
        }
    } else {
        Column {
            Box {
                BasicTextField(
                    value = name.value,
                    onValueChange = { currentText ->
                        name.value = currentText

                        if (name.value.isNotEmpty()) {
                            expanded.value = true

                            displayedSuggestions.clear()
                            displayedSuggestions.addAll(
                                suggestions.filter { it.value.contains(name.value) },
                            )
                        }

                        onTextChange?.invoke(name.value)
                    },
                    textStyle =
                        masterDesignArgs.normalTextStyle.copy(
                            color = overrideForegroundColor ?: masterDesignArgs.mCardTextColor,
                        ),
                    cursorBrush =
                        SolidColor(
                            overrideForegroundColor ?: masterDesignArgs.mCardTextColor,
                        ),
                    decorationBox = { innerTextField ->
                        SearchTextFieldDecorationBox(
                            innerTextField = innerTextField,
                            overrideForegroundColor = overrideForegroundColor,
                            searchTextState = name,
                            onClear = onClear,
                            masterDesignArgs = masterDesignArgs,
                        )
                    },
                    keyboardActions =
                        KeyboardActions(
                            onDone = onDone,
                        ),
                    singleLine = true,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(vertical = 12.dp),
                )

                if (displayedSuggestions.isNotEmpty() && suggestions.isNotEmpty() && expanded.value) {
                    DropdownMenu(
                        expanded = suggestions.isNotEmpty(),
                        onDismissRequest = { },
                        modifier =
                            Modifier
                                .fillMaxWidth(.75f)
                                .heightIn(max = 350.dp),
                        properties = PopupProperties(focusable = false),
                        offset = DpOffset(x = 0.dp, y = 50.dp),
                    ) {
                        displayedSuggestions.forEach { identifiedString ->
                            DropdownMenuItem(onClick = {
                                name.value = identifiedString.value
                                expanded.value = false

                                if (onOptionSelected != null) {
                                    onOptionSelected(identifiedString.id, identifiedString.value)
                                }
                            }) {
                                Text(
                                    text = identifiedString.value,
                                    style = masterDesignArgs.normalTextStyle,
                                    color =
                                        moduleDesignArgs.mCardTextColor
                                            ?: masterDesignArgs.mCardTextColor,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
