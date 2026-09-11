package com.sena.crud.domain.useCase

import com.sena.crud.domain.model.ProductModel
import com.sena.crud.domain.repository.ProductRepository
import javax.inject.Inject

/**
 * Caso de Uso para obtener la lista completa de productos.
 * Su función es pedirle al repositorio los datos y entregarlos a la UI.
 */
class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<ProductModel> {
        return repository.getAllProducts()
    }
}
