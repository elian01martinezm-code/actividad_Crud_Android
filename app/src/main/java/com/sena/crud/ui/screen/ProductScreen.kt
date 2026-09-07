package com.sena.crud.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sena.crud.ui.section.ProductDetails
import com.sena.crud.ui.viewModel.ProductViewModel

@Composable
fun ProductScreen(
    productId: Int,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(productId) {
        viewModel.getProductById(productId)
    }

    // Scaffold proporciona la estructura básica de Material Design (como el botón flotante).
    Scaffold(
        floatingActionButton = {
            // Botón flotante para actualizar manualmente los datos del producto.
            FloatingActionButton(
                onClick = { viewModel.getProductById(productId) }
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Actualizar producto"
                )
            }
        }
    ) { paddingValues ->
        // Box que aplica el paddingValues del Scaffold para evitar que el contenido se solape con las barras del sistema.
        Box(modifier = Modifier.padding(paddingValues)) {
            // Sección encargada de mostrar el estado (cargando, error o éxito con la tarjeta).
            ProductDetails(
                uiState = uiState,
                onRetry = { viewModel.getProductById(productId) }
            )
        }
    }
}
