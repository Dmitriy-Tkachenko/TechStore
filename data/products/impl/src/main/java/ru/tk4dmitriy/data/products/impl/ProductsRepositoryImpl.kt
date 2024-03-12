package ru.tk4dmitriy.data.products.impl

import io.reactivex.rxjava3.core.Single
import ru.tk4dmitriy.data.products.api.Product
import ru.tk4dmitriy.data.products.api.ProductsRepository
import ru.tk4dmitriy.data.products.impl.network.ProductsNetwork
import javax.inject.Inject

internal class ProductsRepositoryImpl @Inject constructor(
    private val productsNetwork: ProductsNetwork
) : ProductsRepository {
    override fun getProducts(): Single<List<Product>> =
        productsNetwork.getProducts().map { resp ->
            resp.products.map {
                Product(
                    id = it.id,
                    title = it.title.toString(),
                    description = it.description.toString(),
                    thumbnail = it.thumbnail.toString(),
                )
            }
        }
}