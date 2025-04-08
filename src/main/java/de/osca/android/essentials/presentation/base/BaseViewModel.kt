package de.osca.android.essentials.presentation.base

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.osca.android.essentials.domain.entity.android.AppProperties
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapperState
import de.osca.android.essentials.presentation.component.toast.ToastState
import de.osca.android.essentials.utils.extensions.failure
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var appProperties: AppProperties

    @Inject
    lateinit var defaultDesignArgs: MasterDesignArgs

    @Inject
    lateinit var errorHandler: DataErrorHandler

    val toastState: MutableState<ToastState> = mutableStateOf(ToastState.NoToast)

    val wrapperState: MutableState<ScreenWrapperState> =
        mutableStateOf(ScreenWrapperState.WaitingInitialization)

    protected fun launchDataLoad(
        onFailure: (Exception) -> Unit = { ex -> defaultOnException(ex) },
        block: suspend () -> Unit
    ): Job {
        return viewModelScope.launch {
            try {
                block()
            } catch (ex: CancellationException) {
                Log.w(TAG, "A job has been cancelled:\n${ex.stackTraceToString()}")
            } catch (ex: Exception) {
                onFailure.invoke(ex)
                Log.e(TAG, ex.stackTraceToString())
            }
        }
    }

    fun longToast(text: String) {
        toastState.value = ToastState.ShowLongToast(text)
    }

    fun shortToast(text: String) {
        toastState.value = ToastState.ShowShortToast(text)
    }

    private fun defaultOnException(ex: Exception) {
        wrapperState.failure(msg = errorHandler.resolveMessage(ex))
    }

    fun printToast(msg: String, context: Context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "BaseViewModel"
    }
}