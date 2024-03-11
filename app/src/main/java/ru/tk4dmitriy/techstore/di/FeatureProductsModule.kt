package ru.tk4dmitriy.techstore.di

import dagger.Module
import dagger.Provides
import ru.tk4dmitriy.data.products.api.DataProductsApi
import ru.tk4dmitriy.data.products.api.ProductsRepository
import ru.tk4dmitriy.data.products.impl.di.DataProductsComponent
import ru.tk4dmitriy.features.products.api.FeatureProductsApi
import ru.tk4dmitriy.features.products.di.FeatureProductsComponentHolder
import ru.tk4dmitriy.features.products.di.FeatureProductsDependencies

@Module
class FeatureProductsModule {
    @Provides
    fun provideDataProducts(): DataProductsApi {
        return DataProductsComponent.initAndGet()
    }

    @Provides
    fun provideFeatureProductsDependencies(
        dataProductsApi: DataProductsApi
    ): FeatureProductsDependencies {
        return object : FeatureProductsDependencies {
           override fun productsRepository(): ProductsRepository =
               dataProductsApi.getProductsRepository()
        }
    }

    @Provides
    fun provideFeatureProducts(
        dependencies: FeatureProductsDependencies
    ): FeatureProductsApi {
        FeatureProductsComponentHolder.init(dependencies)
        return FeatureProductsComponentHolder.get()
    }
}