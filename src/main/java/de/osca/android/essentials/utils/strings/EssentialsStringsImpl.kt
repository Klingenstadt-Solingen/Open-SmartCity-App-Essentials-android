package de.osca.android.essentials.utils.strings

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import de.osca.android.essentials.R
import de.osca.android.essentials.domain.entity.BooleanLiteralType
import java.util.*
import javax.inject.Inject

class EssentialsStringsImpl @Inject constructor(
    @ApplicationContext val context: Context
) : EssentialsStrings {

    override val globalShareVia: String = context.getString(R.string.global_share_via)
    override val globalBack: String = context.getString(R.string.global_back)
    override val globalSearch: String = context.getString(R.string.global_search)
    override val globalOk: String = context.getString(R.string.global_ok)
    override val globalSharedWith: String = context.getString(R.string.global_shared_with)
    override val globalShare: String = context.getString(R.string.global_share)
    override val globalAddToCalendar: String = context.getString(R.string.global_add_to_calendar)
    override val globalSelectDate: String = context.getString(R.string.global_select_date)
    override val globalErrorDialogTitle: String = context.getString(R.string.global_error_dialog_title)
    override val globalTryLater: String = context.getString(R.string.global_try_later)
    override val globalYes: String = context.getString(R.string.global_yes)
    override val globalNo: String = context.getString(R.string.global_no)
    override val globalTryAgain: String = context.getString(R.string.global_try_again)
    override val contentDescriptionBackArrow: String = context.getString(R.string.essentials_content_description_back_arrow)
    override val contentDescriptionShare: String = context.getString(R.string.essentials_content_description_share)
    override val measureKilometer: String = context.getString(R.string.essentials_measure_kilometer)
    override val measureMeter: String = context.getString(R.string.essentials_measure_meter)

    override val errorUnknown: String = getString(R.string.error_unknown)
    override val errorNetwork: String = getString(R.string.error_network)
    override val errorUnprocessable: String = getString(R.string.error_unprocessable)
    override val errorNotFound: String = getString(R.string.error_not_found)
    override val errorNotProcessable: String = getString(R.string.error_not_processable)
    override val errorSocketTimeout: String = getString(R.string.error_socket_timeout)
    override val cityName: String = getString(R.string.city_name)

    override val globalOn: String = getString(R.string.global_on)
    override val globalOff: String = getString(R.string.global_off)

    override val globalShowAll: String = getString(R.string.global_show_all)

    override fun localizedBoolean(value: Boolean, type: BooleanLiteralType): String {
        return when (type) {
            BooleanLiteralType.YES_NO -> if (value) globalYes else globalNo
            BooleanLiteralType.ON_OFF -> if (value) globalOn else globalOff
        }
    }

    override fun getString(@StringRes id: Int, vararg objects: Objects): String {
        return context.getString(id, objects)
    }

}