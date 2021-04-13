package com.am.fakestoreapp.mvp.details

import android.content.Context
import com.am.fakestoreapp.callback.DetailsCallback
import com.am.fakestoreapp.model.CartsRequest
import com.am.fakestoreapp.model.Products
import com.am.fakestoreapp.model.ProductsItem
import com.am.fakestoreapp.room.AppDatabase
import com.am.fakestoreapp.service.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DetailsPresenter(val iView: DetailContract.IView, private val service: ProductService) :
    DetailContract.IPresenter {

    override fun getProductDetails(id: Int) {
        service.getProductDetails(id, object : DetailsCallback {
            override fun success(products: ProductsItem?) {
                iView.success(products)
            }

            override fun failure(message: String?) {
                iView.showEmpty(true)
            }
        })
    }

    override fun addToCart(context: Context, item: ProductsItem) {
//        val date = Calendar.getInstance().time
//        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//        val strDate = formatter.format(date)
//        GlobalScope.launch(Dispatchers.Main) {
//            if (AppDatabase.getDatabase(context).cartDao().exists(item.id)) {
//                AppDatabase.getDatabase(context).cartDao().update(item)
//            } else
//                AppDatabase.getDatabase(context).cartDao().insert(item)
//        }
    }
}