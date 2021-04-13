package com.am.fakestoreapp.service

import com.am.fakestoreapp.api.FakeApi
import com.am.fakestoreapp.callback.DetailsCallback
import com.am.fakestoreapp.callback.ProductCallback
import com.am.fakestoreapp.model.ProductsItem
import com.am.fakestoreapp.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductService {
    private val serviceApi: FakeApi = ApiClient.createApi().create(FakeApi::class.java)

    fun getProducts(callback: ProductCallback) {
        serviceApi.getProducts().enqueue(object : Callback<List<ProductsItem>> {
            override fun onFailure(call: Call<List<ProductsItem>>, t: Throwable) {
                callback.failure(t.message)
            }

            override fun onResponse(
                call: Call<List<ProductsItem>>,
                response: Response<List<ProductsItem>>
            ) {
                if (response.isSuccessful) {
                    callback.success(response.body())

                } else {
                    callback.failure("Something went wrong")
                }
            }
        })
    }

    fun getProductDetails(id: Int, callback: DetailsCallback) {
        serviceApi.getProductItem(id).enqueue(object : Callback<ProductsItem> {
            override fun onFailure(call: Call<ProductsItem>, t: Throwable) {
                callback.failure(t.message)
            }

            override fun onResponse(call: Call<ProductsItem>, response: Response<ProductsItem>) {
                if (response.isSuccessful)
                    callback.success(response.body()) else callback.failure("Something went wrong")
            }
        })
    }

    fun addToCarts(id: Int, callback: DetailsCallback) {
        serviceApi.getProductItem(id).enqueue(object : Callback<ProductsItem> {
            override fun onFailure(call: Call<ProductsItem>, t: Throwable) {
                callback.failure(t.message)
            }

            override fun onResponse(call: Call<ProductsItem>, response: Response<ProductsItem>) {
                if (response.isSuccessful)
                    callback.success(response.body()) else callback.failure("Something went wrong")
            }
        })
    }
}