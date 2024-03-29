package ru.tk4dmitriy.data.products.impl

import io.reactivex.rxjava3.core.Single
import ru.tk4dmitriy.data.products.api.Product
import ru.tk4dmitriy.data.products.api.ProductsRepository
import ru.tk4dmitriy.data.products.impl.network.ProductsNetwork
import javax.inject.Inject

internal class ProductsRepositoryImpl @Inject constructor(
    private val productsNetwork: ProductsNetwork
) : ProductsRepository {
    override fun getProducts(skip: Int, limit: Int): Single<List<Product>> =
        productsNetwork.getProducts(skip = skip, limit = limit).map { resp ->
            resp.products.map {
                Product(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    thumbnail = it.thumbnail,
                    price = it.price
                )
            }
        }
}