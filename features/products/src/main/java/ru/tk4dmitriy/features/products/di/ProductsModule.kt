package ru.tk4dmitriy.features.products.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.tk4dmitriy.core.ui.ViewModelKey
import ru.tk4dmitriy.features.products.ui.ProductsViewModel

@Module
internal interface ProductsModule {
    @Binds
    @[IntoMap ViewModelKey(ProductsViewModel::class)]
    fun provideProductsViewModel(productsViewModel: ProductsViewModel): ViewModel
}