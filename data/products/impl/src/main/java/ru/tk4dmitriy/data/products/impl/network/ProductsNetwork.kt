package ru.tk4dmitriy.data.products.impl.network

import io.reactivex.rxjava3.core.Single
import ru.tk4dmitriy.data.products.impl.network.models.Response

internal interface ProductsNetwork {
    fun getProducts(skip: Int, limit: Int) : Single<Response>
}