package ru.tk4dmitriy.data.products.impl.network

import io.reactivex.rxjava3.core.Single
import ru.tk4dmitriy.data.products.impl.network.api.ProductsApi
import ru.tk4dmitriy.data.products.impl.network.models.Response
import javax.inject.Inject

internal class ProductsNetworkImpl @Inject constructor(
    private val productsApi: ProductsApi
) : ProductsNetwork {
    private val limit = 20
    private var skip = 0
    private var isFirstCall = true
    override fun getProducts(): Single<Response> = productsApi.getProducts(
        skip = getCurrentSkip(),
        limit = limit
    )

    private fun getCurrentSkip(): Int {
        if (isFirstCall) isFirstCall = false
        else skip += limit
        return skip
    }
}