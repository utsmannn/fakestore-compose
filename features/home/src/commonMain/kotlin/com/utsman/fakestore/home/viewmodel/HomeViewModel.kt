package com.utsman.fakestore.home.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.utsman.core.StateEvent
import com.utsman.core.ViewModel
import com.utsman.core.rememberViewModel
import com.utsman.fakestore.home.domain.HomeUseCase
import com.utsman.fakestore.home.domain.Product
import com.utsman.network.ProductApi
import com.utsman.network.ProductResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@Composable
fun rememberHomeViewModel(): HomeViewModel {
    return rememberViewModel(HomeViewModel())
}

class HomeViewModel : ViewModel() {
    private val homeUseCase = HomeUseCase()

    val productList: StateFlow<StateEvent<List<Product>>> get() = homeUseCase.productListEvent

    fun getProduct() = viewModelScope.launch {
        homeUseCase.getProductList()
    }
}