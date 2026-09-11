package com.sena.crud.domain.model

/**
 * Modelo de datos del producto para la capa de Dominio.
 * Representa la información que realmente le interesa a nuestra aplicación,
 * separada de cómo viene el formato desde Internet (DTO).
 */
data class ProductModel (
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double
)
