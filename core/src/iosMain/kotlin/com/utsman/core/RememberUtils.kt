package com.utsman.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember

@Composable
actual fun <T: ViewModel> rememberViewModel(viewModel: T): T {
    val vm = remember { viewModel }
    DisposableEffect(Unit) {
        onDispose {
            vm.clear()
        }
    }

    return vm
}