package com.am.fakestoreapp.callback

import androidx.annotation.NonNull
import com.am.fakestoreapp.model.ProductsItem

interface ProductCallback {
    fun success(@NonNull products: List<ProductsItem>?)
    fun failure(@NonNull message: String?)
}

interface DetailsCallback {
    fun success(@NonNull products: ProductsItem?)
    fun failure(@NonNull message: String?)
}