package ru.tk4dmitriy.features.products.ui

import androidx.annotation.StringRes
import ru.tk4dmitriy.core.ui.Effect

sealed class ProductsEffect : Effect {
    class ShowToast(@StringRes val message: Int) : ProductsEffect()
}