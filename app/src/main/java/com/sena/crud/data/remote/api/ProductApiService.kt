package com.sena.crud.data.remote.api

import com.sena.crud.data.remote.dto.req.product.Product
import com.sena.crud.data.remote.dto.req.product.ProductListResponse
import com.sena.crud.data.remote.dto.req.product.ProductRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Interfaz de Retrofit para definir las rutas (End-points) de la API.
 * Aquí conectamos nuestra app con el servidor de Internet.
 */
interface ProductApiService {
    // Obtener todos los productos
    @GET("products")
    suspend fun getAllProducts(): ProductListResponse

    // Obtener un producto por su ID
    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): Product

    // Enviar datos para crear un nuevo producto
    @POST("products/add")
    suspend fun createProduct(
        @Body product: ProductRequest
    ): Product

    // Enviar datos para actualizar un producto
    @PUT("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int,
        @Body product: ProductRequest
    ): Product

    // Pedir al servidor que elimine un producto
    @DELETE("products/{id}")
    suspend fun deleteProduct(
        @Path("id") id: Int
    ): Product
}
