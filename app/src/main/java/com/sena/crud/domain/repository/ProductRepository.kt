package com.sena.crud.domain.repository

import com.sena.crud.domain.model.ProductModel

/**
 * Interfaz del Repositorio.
 * Define LAS ACCIONES que podemos hacer con los productos (CRUD).
 * No le importa de dónde vienen los datos (API, Base de datos, etc.), solo qué se puede hacer.
 */
interface ProductRepository {
    suspend fun getAllProducts(): List<ProductModel>
    suspend fun getProductById(id: Int): ProductModel
    suspend fun createProduct(product: ProductModel): ProductModel
    suspend fun updateProduct(id: Int, product: ProductModel): ProductModel
    suspend fun deleteProduct(id: Int): Boolean
}
