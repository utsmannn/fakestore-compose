package com.utsman.fakestore.home.domain

import com.utsman.core.Empty
import com.utsman.core.Nol
import com.utsman.core.StateEvent
import com.utsman.core.orNol
import com.utsman.network.ProductResponse

data class Product(
    val id: Int = Int.Nol,
    val name: String = String.Empty,
    val imageUrl: String = String.Empty
) {
    companion object {
        fun mapFromResponse(response: ProductResponse?): Product {
            return Product(
                id = response?.productId.orNol(),
                name = response?.productTitle.orEmpty(),
                imageUrl = response?.productImage.orEmpty()
            )
        }
    }
}