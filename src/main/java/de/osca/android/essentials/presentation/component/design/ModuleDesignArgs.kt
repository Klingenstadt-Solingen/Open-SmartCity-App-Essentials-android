package de.osca.android.essentials.presentation.component.design

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

interface ModuleDesignArgs {
    val mTopBarBackColor: Color?
    val mBottomBarBackColor: Color?
    val mStatusBarBackColor: Color?
    val mCardBackColor: Color?
    val mMenuBackColor: Color?
    val mSheetBackColor: Color?
    val mScreenBackColor: Color?
    val mTopBarTextColor: Color?
    val mBottomBarTextColor: Color?
    val mStatusBarTextColor: Color?
    val mCardTextColor: Color?
    val mMenuTextColor: Color?
    val mSheetTextColor: Color?
    val mScreenTextColor: Color?
    val mHintTextColor: Color?
    val mTextInCard: Boolean?
    val mRootCardSpacing: Dp?
    val mRootBoarderSpacing: Dp?
    val mCardContentPadding: Dp?
    val mConstrainHeight: Dp?
    val mCardElevation: Dp?
    val mSheetElevation: Dp?
    val mShowLessText: Int?
    val mShowMoreText: Int?
    val mHeaderTextColor: Color?
    val mShowMoreTextColor: Color?
    val vShowMoreOption: Boolean
    val vModuleTitle: Int
    val mShapeCard: Dp?
    val mShapeBottomSheet: Shape?
    val mShapeTopSheet: Shape?
    val mShapeSheet: Shape?
    val mButtonBackgroundColor: Color?
    val mButtonContentColor: Color?
    val mSwitchCheckedThumbColor: Color?
    val mSwitchCheckedTrackColor: Color?
    val mSwitchUncheckedThumbColor: Color?
    val mSwitchUncheckedTrackColor: Color?
    val mDropDownBorderColor: Color?
    val mTextFieldFocusedBorderColor: Color?
    val mTextFieldUnfocusedBorderColor: Color?
    val mDialogsBackgroundColor: Color? // delete
    val mDialogsTextColor: Color?
    val mDialogsBackColor: Color?
    val mBorderSpace: Dp?
    val mContentPaddingForMiniCards: Dp?
    val mRoundIconSize: Dp?
    val mIsStatusBarWhite: Boolean?

    @Composable
    fun getButtonColors(): ButtonColors {
        return ButtonDefaults.buttonColors(
            backgroundColor = mButtonBackgroundColor ?: Color.Black,
            contentColor = mButtonContentColor ?: Color.White
        )
    }

    @Composable
    fun getSwitchColors(): SwitchColors {
        return SwitchDefaults.colors(
            checkedThumbColor = mSwitchCheckedThumbColor ?: Color.Red,
            checkedTrackColor = mSwitchCheckedTrackColor ?: Color.Yellow,
            uncheckedThumbColor = mSwitchUncheckedThumbColor ?: Color.Black,
            uncheckedTrackColor = mSwitchUncheckedTrackColor ?: Color.Gray,
        )
    }

    @Composable
    fun getTextFieldColors(): TextFieldColors {
        return TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = mTextFieldFocusedBorderColor ?: Color.Black,
            unfocusedBorderColor = mTextFieldUnfocusedBorderColor ?: Color.Gray
        )
    }
}