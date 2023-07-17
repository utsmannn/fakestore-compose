package com.utsman.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner

@Composable
actual fun <T: ViewModel> rememberViewModel(viewModel: T): T {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val vm = remember { viewModel }
    DisposableEffect(lifecycle) {
        onDispose {
            vm
        }
    }

    return vm
}