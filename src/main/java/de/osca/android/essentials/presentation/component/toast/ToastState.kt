package de.osca.android.essentials.presentation.component.toast

sealed class ToastState {
    class ShowShortToast(val message: String) : ToastState()
    class ShowLongToast(val message: String) : ToastState()
    object NoToast : ToastState()
}
