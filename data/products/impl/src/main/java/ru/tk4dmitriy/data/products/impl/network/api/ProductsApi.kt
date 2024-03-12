package ru.tk4dmitriy.data.products.impl.network.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.tk4dmitriy.data.products.impl.network.models.Response

internal interface ProductsApi {
    @GET("products")
    fun getProducts(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): Single<Response>
}