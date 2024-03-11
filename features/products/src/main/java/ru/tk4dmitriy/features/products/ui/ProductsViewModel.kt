package ru.tk4dmitriy.features.products.ui

import androidx.lifecycle.ViewModel
import ru.tk4dmitriy.data.products.api.ProductsRepository
import ru.tk4dmitriy.features.products.di.FeatureProductsComponentHolder
import javax.inject.Inject

internal class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        FeatureProductsComponentHolder.reset()
    }
}