package ru.tk4dmitriy.features.products.api

import ru.tk4dmitriy.module_injector.BaseAPI

const val TAG = "FRAGMENT_PRODUCTS"
interface FeatureProductsApi : BaseAPI {
    fun getTagFragment(): String = TAG
}