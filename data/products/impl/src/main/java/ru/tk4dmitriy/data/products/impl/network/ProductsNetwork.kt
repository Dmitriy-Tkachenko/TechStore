package ru.tk4dmitriy.data.products.impl.network

import io.reactivex.rxjava3.core.Single
import ru.tk4dmitriy.data.products.impl.network.models.Response

interface ProductsNetwork {
    fun getProducts() : Single<Response>
}