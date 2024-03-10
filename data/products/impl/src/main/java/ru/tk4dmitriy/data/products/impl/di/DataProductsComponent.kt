package ru.tk4dmitriy.data.products.impl.di

import dagger.Component
import ru.tk4dmitriy.core.network.NetworkModule
import ru.tk4dmitriy.data.products.impl.network.ProductsNetwork
import ru.tk4dmitriy.data.products.impl.network.api.ProductsApi

@Component(modules = [
    NetworkModule::class,
    ProductsApiModule::class,
    ProductsNetworkModule::class
])
interface DataProductsComponent {
    fun getProductsApi(): ProductsApi
    fun getProductsNetwork(): ProductsNetwork

    @Component.Builder
    interface Builder {
        fun build(): DataProductsComponent
    }
}