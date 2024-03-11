package ru.tk4dmitriy.data.products.impl.di

import dagger.Binds
import dagger.Module
import ru.tk4dmitriy.data.products.api.ProductsRepository
import ru.tk4dmitriy.data.products.impl.ProductsRepositoryImpl
import ru.tk4dmitriy.data.products.impl.network.ProductsNetwork
import ru.tk4dmitriy.data.products.impl.network.ProductsNetworkImpl

@Module
internal interface ProductsNetworkModule {

    @Binds
    fun provideProductsNetwork(productsNetworkImpl: ProductsNetworkImpl): ProductsNetwork

    @Binds
    fun provideProductsRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository
}