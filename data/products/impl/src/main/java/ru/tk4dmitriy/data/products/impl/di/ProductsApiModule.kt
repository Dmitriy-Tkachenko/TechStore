package ru.tk4dmitriy.data.products.impl.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.tk4dmitriy.data.products.impl.network.api.ProductsApi

@Module
internal class ProductsApiModule {

    @Provides
    fun provideProductsApi(retrofit: Retrofit): ProductsApi =
        retrofit.create(ProductsApi::class.java)
}