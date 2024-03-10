package ru.tk4dmitriy.data.products.api

import io.reactivex.rxjava3.core.Single

interface ProductsRepository {
    fun getProducts(): Single<List<Product>>
}