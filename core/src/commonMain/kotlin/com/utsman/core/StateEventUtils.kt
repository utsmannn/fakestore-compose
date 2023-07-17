package com.utsman.core

import androidx.compose.runtime.Composable

@Composable
fun <T: Any>StateEvent<T>.onLoading(content: @Composable () -> Unit) {
    if (this is StateEvent.Loading) {
        content.invoke()
    }
}

@Composable
fun <T: Any>StateEvent<T>.onSuccess(content: @Composable (T) -> Unit) {
    if (this is StateEvent.Success) {
        content.invoke(this.data)
    }
}

@Composable
fun <T: Any>StateEvent<T>.onFailure(content: @Composable (Throwable) -> Unit) {
    if (this is StateEvent.Failure) {
        content.invoke(this.exception)
    }
}

@Composable
fun <T: Any>StateEvent<T>.onIdle(content: @Composable () -> Unit) {
    if (this is StateEvent.Idle) {
        content.invoke()
    }
}

