package ru.tk4dmitriy.data.products.impl.di

import dagger.Component
import ru.tk4dmitriy.core.network.NetworkModule
import ru.tk4dmitriy.data.products.api.DataProductsApi
import ru.tk4dmitriy.data.products.api.ProductsRepository
import ru.tk4dmitriy.data.products.impl.network.ProductsNetwork
import ru.tk4dmitriy.data.products.impl.network.api.ProductsApi

@Component(modules = [
    NetworkModule::class,
    ProductsApiModule::class,
    ProductsNetworkModule::class
])
abstract class DataProductsComponent : DataProductsApi {
    internal abstract fun getProductsApi(): ProductsApi
    internal abstract fun getProductsNetwork(): ProductsNetwork
    abstract override fun getProductsRepository(): ProductsRepository

    companion object {
        fun initAndGet(): DataProductsApi {
            return DaggerDataProductsComponent.builder().build()
        }
    }
}