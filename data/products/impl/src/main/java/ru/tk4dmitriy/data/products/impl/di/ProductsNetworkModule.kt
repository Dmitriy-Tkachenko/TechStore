package ru.tk4dmitriy.data.products.impl.di

import dagger.Binds
import dagger.Module
import ru.tk4dmitriy.data.products.impl.network.ProductsNetwork
import ru.tk4dmitriy.data.products.impl.network.ProductsNetworkImpl

@Module
interface ProductsNetworkModule {

    @Binds
    fun provideProductsNetworkModule(productsNetworkImpl: ProductsNetworkImpl): ProductsNetwork
}