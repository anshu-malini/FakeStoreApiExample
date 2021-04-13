package com.am.fakestoreapp.model

import com.google.gson.annotations.SerializedName

data class CartsRequest(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("products")
	val products: List<Products?>? = null
)

data class Products(

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("productId")
	val productId: Int? = null
)
