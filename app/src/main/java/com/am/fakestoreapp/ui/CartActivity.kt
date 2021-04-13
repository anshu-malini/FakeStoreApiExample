package com.am.fakestoreapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.am.fakestoreapp.R
import com.am.fakestoreapp.adapter.CartAdapter
import com.am.fakestoreapp.model.ProductsItem
import com.am.fakestoreapp.mvp.home.HomeContract
import com.am.fakestoreapp.room.AppDatabase
import kotlinx.android.synthetic.main.cart_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartActivity : AppCompatActivity(), HomeContract.IView {
    var totalAmount: Double = 0.0

//    private val presenter =
//        HomePresenter(this, ProductService())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_activity)
        initUI()
        reloadData()
//        swipeRefreshLayout.setOnRefreshListener {
//            reloadData()
//            swipeRefreshLayout.isRefreshing = false
//        }

        GlobalScope.launch(Dispatchers.Main) {
            success(AppDatabase.getDatabase(this@CartActivity).cartDao().getAllCartItem())
        }
    }

    override fun initUI() {
        checkoutBtn.setOnClickListener {
            Toast.makeText(this, "Checkout clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun reloadData() {
        isShowProgress(true)
//        presenter.getProductList()
    }

    override fun success(list: List<ProductsItem>?) {
        isShowProgress(false)
        if (list != null) {
            total.text = "Total: $ ${calculateTotal(list)}"

            noItem.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@CartActivity)
                adapter = CartAdapter(
                    this@CartActivity,
                    list.sortedBy { it.price }) { productsItem: ProductsItem, i: Int ->
                    adapter?.notifyItemRemoved(i)
                    GlobalScope.launch(Dispatchers.Main) {
                        AppDatabase.getDatabase(this@CartActivity).cartDao()
                            .deleteOneItem(productsItem.id)
                    }
                }
//                adapter = CartAdapter(this@CartActivity, list.sortedBy { it.price }) {
//
//                    adapter?.notifyItemRemoved(9)
//                    startActivity(
//                        Intent(
//                            this@CartActivity,
//                            ProductDetails::class.java
//                        ).apply { putExtra(INTENT_PRODUCT_ID, it.id) })
//                }
            }
        } else {
            showEmpty()
        }
    }

    fun calculateTotal(list: List<ProductsItem>): Double {
        for (item in list) {
            totalAmount += item.price!!
        }
        return totalAmount
    }

    override fun showEmpty() {
        isShowProgress(false)
        noItem.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    override fun isShowProgress(showProgress: Boolean) {
        if (showProgress) {
            progressDot.visibility = View.VISIBLE
            progressDot.startAnimation()
        } else {
            progressDot.stopAnimation()
            progressDot.visibility = View.GONE
        }
    }
}