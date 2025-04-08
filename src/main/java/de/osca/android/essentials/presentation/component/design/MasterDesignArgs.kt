package de.osca.android.essentials.presentation.component.design

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.SwitchColors
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.vanpra.composematerialdialogs.datetime.date.DatePickerColors
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.TimePickerColors
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults

interface MasterDesignArgs : WidgetDesignArgs {
    val reallyBigTextStyle: TextStyle
    val bigTextStyle: TextStyle
    val normalTextStyle: TextStyle
    val buttonTextStyle: TextStyle
    val overlineTextStyle: TextStyle
    val captionTextStyle: TextStyle
    val bodyTextStyle: TextStyle
    val subtitleTextStyle: TextStyle
    val subtitleBoldTextStyle: TextStyle
    val highlightColor: Color
    val appSpecificColor: Color
    val errorTextColor: Color
    val buttonDefaultElevation: Dp
    val buttonPressedElevation: Dp
    val buttonDisabledElevation: Dp
    val datePickerDialogHeaderBackgroundColor: Color
    val datePickerDialogHeaderTextColor: Color
    val datePickerDialogDateActiveBackgroundColor: Color
    val datePickerDialogDateActiveTextColor: Color
    val timePickerDialogInactiveBackgroundColor: Color
    val timePickerDialogActiveBackgroundColor: Color
    val timePickerDialogActiveTextColor: Color
    val timePickerDialogSelectorColor: Color
    val timePickerDialogSelectorTextColor: Color
    val dividerColor: Color
    val weatherGradientColorTop: Color
    val weatherGradientColorBottom: Color
    val spaceDefault: Dp
    val spaceCards: Dp
    val spaceList: Dp
    val lightPrimary: Color

    val mTopBarBackColor: Color
    val mBottomBarBackColor: Color
    val mStatusBarBackColor: Color
    val mCardBackColor: Color
    val mMenuBackColor: Color
    val mSheetBackColor: Color
    val mScreenBackColor: Color
    val mTopBarTextColor: Color
    val mBottomBarTextColor: Color
    val mStatusBarTextColor: Color
    val mCardTextColor: Color
    val mMenuTextColor: Color
    val mSheetTextColor: Color
    val mScreenTextColor: Color
    val mHintTextColor: Color
    val mTextInCard: Boolean
    val mRootCardSpacing: Dp
    val mRootBoarderSpacing: Dp
    val mCardContentPadding: Dp
    val mConstrainHeight: Dp
    val mCardElevation: Dp
    val mSheetElevation: Dp
    val mShowLessText: Int
    val mShowMoreText: Int
    val mHeaderTextColor: Color
    val mShowMoreTextColor: Color
    val vShowMoreOption: Boolean
    val vModuleTitle: Int
    val mShapeCard: Dp
    val mShapeBottomSheet: Shape
    val mShapeTopSheet: Shape
    val mShapeSheet: Shape
    val mButtonBackgroundColor: Color
    val mButtonContentColor: Color
    val mSwitchCheckedThumbColor: Color
    val mSwitchCheckedTrackColor: Color
    val mSwitchUncheckedThumbColor: Color
    val mSwitchUncheckedTrackColor: Color
    val mDropDownBorderColor: Color
    val mTextFieldFocusedBorderColor: Color
    val mTextFieldUnfocusedBorderColor: Color
    val mTextFieldLabelColor: Color
    val mDialogsTextColor: Color
    val mDialogsBackColor: Color
    val mBorderSpace: Dp
    val mContentPaddingForMiniCards: Dp
    val mRoundIconSize: Dp
    val mIsStatusBarWhite: Boolean

    @Composable
    fun getButtonColors(): ButtonColors {
        return ButtonDefaults.buttonColors(
            backgroundColor = mButtonBackgroundColor,
            contentColor = mButtonContentColor,
        )
    }

    @Composable
    fun getSwitchColors(): SwitchColors {
        return SwitchDefaults.colors(
            checkedThumbColor = mSwitchCheckedThumbColor,
            checkedTrackColor = mSwitchCheckedTrackColor,
            uncheckedThumbColor = mSwitchUncheckedThumbColor,
            uncheckedTrackColor = mSwitchUncheckedTrackColor,
        )
    }

    @Composable
    fun getTextFieldColors(): TextFieldColors {
        return TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = mTextFieldFocusedBorderColor,
            unfocusedBorderColor = mTextFieldUnfocusedBorderColor,
        )
    }

    @Composable
    fun getButtonElevation(): ButtonElevation {
        return ButtonDefaults.elevation(
            defaultElevation = buttonDefaultElevation,
            pressedElevation = buttonPressedElevation,
            disabledElevation = buttonDisabledElevation,
        )
    }

    @Composable
    fun getDatePickerColors(): DatePickerColors {
        return DatePickerDefaults.colors(
            headerBackgroundColor = datePickerDialogHeaderBackgroundColor,
            headerTextColor = datePickerDialogHeaderTextColor,
            dateActiveBackgroundColor = datePickerDialogDateActiveBackgroundColor,
            dateActiveTextColor = datePickerDialogDateActiveTextColor,
        )
    }

    @Composable
    fun getTimePickerColors(): TimePickerColors {
        return TimePickerDefaults.colors(
            inactiveBackgroundColor = timePickerDialogInactiveBackgroundColor,
            activeBackgroundColor = timePickerDialogActiveBackgroundColor,
            activeTextColor = timePickerDialogActiveTextColor,
            selectorColor = timePickerDialogSelectorColor,
            selectorTextColor = timePickerDialogSelectorTextColor,
        )
    }

    @Composable
    fun getWeatherGradient(): Brush {
        return Brush.verticalGradient(
            colors =
                listOf(
                    weatherGradientColorTop,
                    weatherGradientColorBottom,
                ),
        )
    }

    @Composable
    fun getArtGradient(): Brush {
        return Brush.verticalGradient(
            colors =
                listOf(
                    weatherGradientColorTop,
                    weatherGradientColorTop,
                ),
        )
    }
}

val LocalMasterDesignArgs =
    compositionLocalOf<MasterDesignArgs> { error("No CompositionLocal for LocalMasterDesignArgs") }
