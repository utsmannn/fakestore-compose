package com.utsman.fakestore.home.domain

import com.utsman.network.ProductApi
import com.utsman.network.ProductResponse

class HomeRepository {
    private val productApi: ProductApi = ProductApi()

    suspend fun getProductResponse(): List<ProductResponse> = productApi.getProducts()
}