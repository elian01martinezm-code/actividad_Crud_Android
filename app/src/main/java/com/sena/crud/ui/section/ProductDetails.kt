package com.sena.crud.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sena.crud.domain.model.ProductModel
import com.sena.crud.ui.component.ProductCard
import com.sena.crud.ui.state.ProductUIState

/**
 * Sección que decide qué mostrar según el estado de la carga.
 */
@Composable
fun ProductDetails(
    uiState: ProductUIState,
    onRetry: () -> Unit,
    onEdit: (ProductModel) -> Unit,
    onDelete: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            // Caso 1: Cargando datos
            uiState.isLoading && uiState.products.isEmpty() -> {
                CircularProgressIndicator()
            }
            // Caso 2: Error (ej. sin internet)
            uiState.errorMessage != null -> {
                Text(
                    text = uiState.errorMessage,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Button(onClick = onRetry) {
                    Text(text = "Reintentar")
                }
            }
            // Caso 3: No se encontraron resultados en la búsqueda
            uiState.products.isEmpty() -> {
                Text(
                    text = "No se encontraron productos.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            // Caso 4: Mostrar la lista de productos
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 80.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.products) { product ->
                        ProductCard(
                            product = product,
                            onEdit = { onEdit(product) },
                            onDelete = { onDelete(product.id) }
                        )
                    }
                }
            }
        }
    }
}
