package com.am.fakestoreapp.api

import com.am.fakestoreapp.model.ProductsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeApi {

    @GET("products")
    fun getProducts(): Call<List<ProductsItem>>

    @GET("products/{id}")
    fun getProductItem(@Path("id") id: Int): Call<ProductsItem>
}