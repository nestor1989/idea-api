package com.idea3d.spring.api.model

// Clase para la información de paginación
data class Pagination(
    val currentPage: Int,
    val pageSize: Int,
    val totalItems: Long,
    val totalPages: Int
)

// Clase para la respuesta paginada
data class PaginatedResponse<T>(
    val pagination: Pagination,
    val results: List<T>
)