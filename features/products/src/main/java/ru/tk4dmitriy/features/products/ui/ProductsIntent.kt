package ru.tk4dmitriy.features.products.ui

import ru.tk4dmitriy.core.ui.Intent

sealed class ProductsIntent : Intent {
    object Init : ProductsIntent()
    object Refresh : ProductsIntent()
    class LoadMore(val skip: Int) : ProductsIntent()
}