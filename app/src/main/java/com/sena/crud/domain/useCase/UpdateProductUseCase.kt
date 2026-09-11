package com.sena.crud.domain.useCase

import com.sena.crud.domain.model.ProductModel
import com.sena.crud.domain.repository.ProductRepository
import javax.inject.Inject

/**
 * Caso de Uso para actualizar un producto existente.
 */
class UpdateProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Int, product: ProductModel): ProductModel {
        return repository.updateProduct(id, product)
    }
}
