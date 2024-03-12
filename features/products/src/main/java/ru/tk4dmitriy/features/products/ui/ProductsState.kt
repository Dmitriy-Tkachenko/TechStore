package ru.tk4dmitriy.features.products.ui

import ru.tk4dmitriy.core.ui.State

data class ProductsState(
    val loading: Boolean = false,
    val loadingMore: Boolean = false,
    val fullData: Boolean = false,
    val error: Throwable? = null,
    val data: List<ProductUi> = emptyList(),
) : State

data class ProductUi(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnails: String
)