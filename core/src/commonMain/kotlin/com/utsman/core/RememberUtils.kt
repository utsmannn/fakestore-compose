package com.utsman.core

import androidx.compose.runtime.Composable

@Composable
expect fun <T: ViewModel> rememberViewModel(viewModel: T): T