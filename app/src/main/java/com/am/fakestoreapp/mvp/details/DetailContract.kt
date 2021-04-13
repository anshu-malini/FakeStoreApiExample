package com.am.fakestoreapp.mvp.details

import android.content.Context
import com.am.fakestoreapp.model.ProductsItem

class DetailContract {
    interface IView {
        fun updateUI(item: ProductsItem?)
        fun success(item: ProductsItem?)
        fun showEmpty(isShow: Boolean)
        fun isShowProgress(showProgress: Boolean)
    }

    interface IPresenter {
        fun getProductDetails(id: Int)
        fun addToCart(context: Context, item: ProductsItem)
    }
}