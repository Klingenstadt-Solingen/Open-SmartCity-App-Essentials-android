package de.osca.android.essentials.utils.strings

import androidx.annotation.StringRes
import de.osca.android.essentials.domain.entity.BooleanLiteralType
import java.util.*

interface EssentialsStrings {
    val cityName: String

    val globalShareVia: String
    val globalBack: String
    val globalSearch: String
    val globalOk: String
    val globalSharedWith: String
    val globalShare: String
    val globalAddToCalendar: String
    val globalSelectDate: String
    val globalErrorDialogTitle: String
    val globalTryLater: String
    val globalYes: String
    val globalNo: String
    val globalTryAgain: String
    val contentDescriptionBackArrow: String
    val contentDescriptionShare: String
    val measureKilometer: String
    val measureMeter: String
    val globalOn: String
    val globalOff: String
    val globalShowAll: String

    val errorUnknown: String
    val errorNetwork: String
    val errorUnprocessable: String
    val errorNotFound: String
    val errorNotProcessable: String
    val errorSocketTimeout: String

    fun localizedBoolean(
        value: Boolean,
        type: BooleanLiteralType = BooleanLiteralType.YES_NO
    ): String

    fun getString(@StringRes id: Int, vararg objects: Objects): String
}