package de.osca.android.essentials.presentation.component.screen_wrapper

sealed class ScreenWrapperState(val msg: String?) {
    object WaitingInitialization : ScreenWrapperState(null)
    object Loading : ScreenWrapperState(null)
    object DisplayContent : ScreenWrapperState(null)
    class Failure(msg: String?): ScreenWrapperState(msg)
}