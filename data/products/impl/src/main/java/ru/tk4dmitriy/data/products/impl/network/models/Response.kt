package ru.tk4dmitriy.data.products.impl.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Response(
    @SerialName("products") val products: List<Product>
)

@Serializable
class Product(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("thumbnail") val thumbnail: String
)