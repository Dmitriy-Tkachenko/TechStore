package ru.tk4dmitriy.data.products.api

import ru.tk4dmitriy.module_injector.BaseAPI

interface DataProductsApi : BaseAPI {
    fun getProductsRepository(): ProductsRepository
}