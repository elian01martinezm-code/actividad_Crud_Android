package com.sena.crud.data.remote.dto.req.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductListResponse(
    @Json(name = "products")
    val products: List<Product>,
    @Json(name = "total")
    val total: Int,
    @Json(name = "skip")
    val skip: Int,
    @Json(name = "limit")
    val limit: Int
)
