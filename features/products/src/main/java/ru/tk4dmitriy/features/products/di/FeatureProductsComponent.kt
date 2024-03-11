package ru.tk4dmitriy.features.products.di

import dagger.Component
import ru.tk4dmitriy.core.ui.ViewModelModule
import ru.tk4dmitriy.features.products.api.FeatureProductsApi
import ru.tk4dmitriy.features.products.ui.ProductsFragment

@Component(
    dependencies = [FeatureProductsDependencies::class],
    modules = [ProductsModule::class, ViewModelModule::class]
)
internal abstract class FeatureProductsComponent : FeatureProductsApi {
    internal abstract fun inject(productsFragment: ProductsFragment)

    companion object {
        fun initAndGet(featureProductsDependencies: FeatureProductsDependencies): FeatureProductsComponent {
            return DaggerFeatureProductsComponent.builder()
                .featureProductsDependencies(featureProductsDependencies)
                .build()

        }
    }
}