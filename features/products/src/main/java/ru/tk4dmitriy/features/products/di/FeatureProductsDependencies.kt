package ru.tk4dmitriy.features.products.di

import ru.tk4dmitriy.data.products.api.ProductsRepository
import ru.tk4dmitriy.module_injector.BaseDependencies

interface FeatureProductsDependencies : BaseDependencies {
    fun productsRepository(): ProductsRepository
}