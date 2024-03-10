package ru.tk4dmitriy.data.products.impl.network

import io.reactivex.rxjava3.core.Single
import ru.tk4dmitriy.data.products.impl.network.api.ProductsApi
import ru.tk4dmitriy.data.products.impl.network.models.Response

class ProductsNetworkImpl(private val productsApi: ProductsApi) : ProductsNetwork {
    override fun getProducts(): Single<Response> = productsApi.getProducts()
}