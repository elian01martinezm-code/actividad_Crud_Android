package com.sena.crud.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sena.crud.domain.model.ProductModel
import com.sena.crud.ui.component.ProductFormDialog
import com.sena.crud.ui.section.ProductDetails
import com.sena.crud.ui.viewModel.ProductViewModel

/**
 * Pantalla Principal de la aplicación.
 * Aquí es donde se dibuja todo lo que el usuario ve y toca.
 */
@Composable
fun ProductScreen(
    viewModel: ProductViewModel = hiltViewModel()
) {
    // Escuchamos el estado del ViewModel. Si el estado cambia, Compose redibuja la pantalla.
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    // Estados locales para controlar qué se muestra en la pantalla (como el diálogo)
    var showDialog by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<ProductModel?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    // Scaffold nos da una estructura estándar: espacio para botones, barras y contenido.
    Scaffold(
        floatingActionButton = {
            Column {
                // Botón para agregar nuevo producto
                FloatingActionButton(
                    onClick = {
                        selectedProduct = null
                        showDialog = true
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar producto")
                }
                // Botón para recargar desde internet
                FloatingActionButton(
                    onClick = { viewModel.getAllProducts() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Actualizar productos"
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            
            // Barra de búsqueda (TextField)
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    viewModel.searchProducts(it) // Filtramos mientras el usuario escribe
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Buscar por ID o Nombre...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true
            )

            // Mostramos la lista o el estado de carga
            ProductDetails(
                uiState = uiState,
                onRetry = { viewModel.getAllProducts() },
                onEdit = { product ->
                    selectedProduct = product
                    showDialog = true
                },
                onDelete = { id ->
                    viewModel.deleteProduct(id)
                }
            )
        }

        // Si showDialog es verdadero, mostramos la ventana emergente
        if (showDialog) {
            ProductFormDialog(
                product = selectedProduct,
                onDismiss = { showDialog = false },
                onConfirm = { title, desc, cat, price ->
                    if (selectedProduct == null) {
                        viewModel.createProduct(title, desc, cat, price)
                    } else {
                        viewModel.updateProduct(selectedProduct!!.id, title, desc, cat, price)
                    }
                }
            )
        }
    }
}
