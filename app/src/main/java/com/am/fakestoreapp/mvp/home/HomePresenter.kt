package com.am.fakestoreapp.mvp.home

import com.am.fakestoreapp.callback.ProductCallback
import com.am.fakestoreapp.model.ProductsItem
import com.am.fakestoreapp.service.ProductService

class HomePresenter(val iView: HomeContract.IView, val service: ProductService ) :
    HomeContract.IPresenter {

    override fun getProductList() {
        service.getProducts(object : ProductCallback {
            override fun success(products: List<ProductsItem>?) {
                iView.success(products)
            }

            override fun failure(message: String?) {
                iView.showEmpty()
            }
        })
    }
}