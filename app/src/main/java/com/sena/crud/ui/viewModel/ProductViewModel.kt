package com.sena.crud.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sena.crud.domain.model.ProductModel
import com.sena.crud.domain.useCase.CreateProductUseCase
import com.sena.crud.domain.useCase.DeleteProductUseCase
import com.sena.crud.domain.useCase.GetAllProductsUseCase
import com.sena.crud.domain.useCase.GetProductUseCase
import com.sena.crud.domain.useCase.UpdateProductUseCase
import com.sena.crud.ui.state.ProductUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * El ViewModel es el "Cerebro" de la UI.
 * Gestiona el estado y conecta la pantalla con la lógica de negocio (UseCases).
 */
@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val createProductUseCase: CreateProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase
): ViewModel() {
    
    // El estado de la pantalla que la UI estará observando
    private val _uiState = MutableStateFlow(ProductUIState())
    val uiState: StateFlow<ProductUIState> = _uiState.asStateFlow()

    // Lista en memoria para que no se borren los productos creados/editados
    private var allProductsList = mutableListOf<ProductModel>()
    private var searchQuery = ""

    init {
        // Cargamos los productos nada más abrir la app
        getAllProducts()
    }

    /**
     * Traduce los errores técnicos a mensajes sencillos para el usuario.
     */
    private fun translateError(e: Exception): String {
        val message = e.message ?: ""
        return when {
            message.contains("Unable to resolve host", true) -> "No hay conexión a internet. Revisa tu red."
            message.contains("timeout", true) -> "La conexión ha expirado. Intenta de nuevo."
            message.contains("404", true) -> "Producto no encontrado."
            message.contains("500", true) -> "Error interno del servidor. Intenta más tarde."
            else -> "Ocurrió un error inesperado: $message"
        }
    }

    /**
     * Trae todos los productos desde el servidor y los mezcla con los locales.
     */
    fun getAllProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val result = getAllProductsUseCase()
                // Mantenemos los productos que el usuario creó pero que no están en el servidor real
                val localOnly = allProductsList.filter { local -> result.none { it.id == local.id } }
                allProductsList = (localOnly + result).toMutableList()
                
                applyFilterAndRefresh()
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = translateError(e)) }
            }
        }
    }

    /**
     * Función para buscar productos por nombre o ID.
     */
    fun searchProducts(query: String) {
        searchQuery = query
        applyFilterAndRefresh()
    }

    /**
     * Aplica el filtro de búsqueda y actualiza lo que el usuario ve.
     */
    private fun applyFilterAndRefresh() {
        val filteredList = if (searchQuery.isBlank()) {
            allProductsList
        } else {
            allProductsList.filter {
                it.id.toString() == searchQuery || 
                it.title.contains(searchQuery, ignoreCase = true) ||
                it.category.contains(searchQuery, ignoreCase = true)
            }
        }
        _uiState.update { it.copy(isLoading = false, products = filteredList) }
    }

    fun createProduct(title: String, description: String, category: String, price: Double) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val newProduct = ProductModel(0, title, description, category, price)
                val result = createProductUseCase(newProduct)
                
                // Forzamos un ID secuencial basado en nuestra lista local (ej: 29, 30...)
                val nextId = (allProductsList.maxOfOrNull { it.id } ?: 0) + 1
                val finalProduct = result.copy(id = nextId)
                
                allProductsList.add(0, finalProduct)
                applyFilterAndRefresh()
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = translateError(e)) }
            }
        }
    }

    fun updateProduct(id: Int, title: String, description: String, category: String, price: Double) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val productUpdate = ProductModel(id, title, description, category, price)
                val result = updateProductUseCase(id, productUpdate)
                
                val index = allProductsList.indexOfFirst { it.id == id }
                if (index != -1) {
                    allProductsList[index] = result
                }
                applyFilterAndRefresh()
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = translateError(e)) }
            }
        }
    }

    fun deleteProduct(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                deleteProductUseCase(id)
                allProductsList.removeAll { it.id == id }
                applyFilterAndRefresh()
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = translateError(e)) }
            }
        }
    }
}
