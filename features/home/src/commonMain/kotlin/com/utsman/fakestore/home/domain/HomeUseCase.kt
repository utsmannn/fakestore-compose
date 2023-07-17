package com.utsman.fakestore.home.domain

import com.utsman.network.Reducer

class HomeUseCase {
    private val homeRepository = HomeRepository()
    private val productListReducer = Reducer<List<Product>>()

    val productListEvent = productListReducer.dataFlow

    suspend fun getProductList() {
        productListReducer.transform(
            call = {
                homeRepository.getProductResponse()
            },
            mapper = {
                it.map { item -> Product.mapFromResponse(item) }
            }
        )
    }
}