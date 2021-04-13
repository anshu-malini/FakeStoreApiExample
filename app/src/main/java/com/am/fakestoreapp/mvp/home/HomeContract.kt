package com.am.fakestoreapp.mvp.home

import com.am.fakestoreapp.model.ProductsItem

class HomeContract {
    interface IView {
        fun initUI()
        fun reloadData()
        fun success(list: List<ProductsItem>?)
        fun showEmpty()
        fun isShowProgress(boolean: Boolean)
    }

    interface IPresenter {
        fun getProductList()
    }
}