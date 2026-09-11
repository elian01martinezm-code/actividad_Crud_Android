package com.sena.crud.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sena.crud.domain.model.ProductModel

@Composable
fun ProductFormDialog(
    product: ProductModel? = null,
    onDismiss: () -> Unit,
    onConfirm: (title: String, description: String, category: String, price: Double) -> Unit
) {
    var title by remember { mutableStateOf(product?.title ?: "") }
    var description by remember { mutableStateOf(product?.description ?: "") }
    var category by remember { mutableStateOf(product?.category ?: "") }
    var price by remember { mutableStateOf(product?.price?.toString() ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = if (product == null) "Nuevo Producto" else "Editar Producto") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Categoría") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Precio") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(title, description, category, price.toDoubleOrNull() ?: 0.0)
                    onDismiss()
                }
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
