package com.am.fakestoreapp.model

import com.google.gson.annotations.SerializedName

data class CartResponse(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("products")
	val products: List<Products?>? = null
)
