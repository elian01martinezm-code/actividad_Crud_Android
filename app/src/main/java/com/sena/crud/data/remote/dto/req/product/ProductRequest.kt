package com.sena.crud.data.remote.dto.req.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductRequest(
    @Json(name = "title")
    val title: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "category")
    val category: String,
    @Json(name = "price")
    val price: Double
)
