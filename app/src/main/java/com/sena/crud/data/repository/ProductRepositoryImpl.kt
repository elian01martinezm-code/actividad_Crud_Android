package com.sena.crud.data.repository

import com.sena.crud.data.mapper.toDomain
import com.sena.crud.data.remote.api.ProductApiService
import com.sena.crud.data.remote.dto.req.product.ProductRequest
import com.sena.crud.domain.model.ProductModel
import com.sena.crud.domain.repository.ProductRepository
import javax.inject.Inject

/**
 * Implementación del Repositorio.
 * Aquí es donde realmente sucede el trabajo de ir a Internet usando la API.
 */
class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApiService
): ProductRepository {

    override suspend fun getAllProducts(): List<ProductModel> {
        // Pedimos la lista a la API y convertimos cada producto a nuestro modelo de Dominio
        return api.getAllProducts().products.map { it.toDomain() }
    }

    override suspend fun getProductById(id: Int): ProductModel {
        // Buscamos uno solo por su ID
        return api.getProductById(id).toDomain()
    }

    override suspend fun createProduct(product: ProductModel): ProductModel {
        // Convertimos nuestro modelo de dominio a una petición para el servidor
        val request = ProductRequest(
            title = product.title,
            description = product.description,
            category = product.category,
            price = product.price
        )
        return api.createProduct(request).toDomain()
    }

    override suspend fun updateProduct(id: Int, product: ProductModel): ProductModel {
        val request = ProductRequest(
            title = product.title,
            description = product.description,
            category = product.category,
            price = product.price
        )
        return api.updateProduct(id, request).toDomain()
    }

    override suspend fun deleteProduct(id: Int): Boolean {
        // DummyJSON devuelve el producto eliminado como confirmación
        api.deleteProduct(id)
        return true
    }
}
