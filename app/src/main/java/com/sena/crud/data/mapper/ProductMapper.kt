package com.sena.crud.data.mapper

import com.sena.crud.data.remote.dto.req.product.Product
import com.sena.crud.domain.model.ProductModel

/**
 * Mapeador (Mapper).
 * Sirve para transformar los datos que vienen de Internet (DTO) a los datos
 * que usamos en nuestra aplicación (Model).
 * Esto evita que si la API cambia, tengamos que cambiar toda la app.
 */
fun Product.toDomain(): ProductModel {
    return ProductModel (
        id = id,
        title = title ?: "Sin título",
        description = description ?: "Sin descripción",
        category = category ?: "General",
        price = price ?: 0.0
    )
}
