package com.utsman.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    @SerialName("id")
    val productId: Int? = null,

    @SerialName("title")
    val productTitle: String? = null,

    @SerialName("price")
    val productPrice: Double? = null,

    @SerialName("description")
    val productDescription: String? = null,

    @SerialName("category")
    val productCategory: String? = null,

    @SerialName("image")
    val productImage: String? = null,

    @SerialName("rating")
    val productRating: RatingResponse? = null
)

@Serializable
data class RatingResponse(
    val rate: Double? = null,
    val count: Int? = null
)