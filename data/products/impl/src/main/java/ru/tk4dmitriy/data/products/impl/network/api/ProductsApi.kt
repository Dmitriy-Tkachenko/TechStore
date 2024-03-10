package ru.tk4dmitriy.data.products.impl.network.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.tk4dmitriy.data.products.impl.network.models.Response

interface ProductsApi {
    @GET("products")
    fun getProducts(): Single<Response>
}