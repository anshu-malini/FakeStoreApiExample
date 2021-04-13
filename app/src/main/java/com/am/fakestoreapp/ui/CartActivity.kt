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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_activity)
        initUI()
        reloadData()

        GlobalScope.launch {
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
    }

    override fun success(itemList: List<ProductsItem>?) {
        isShowProgress(false)
        if (itemList != null && itemList.isNotEmpty()) {
            val list = itemList.toMutableList()
            total.text = "Total: $ ${calculateTotal(list)}"

            noItem.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@CartActivity)
                adapter = CartAdapter(
                    this@CartActivity,
                    list
                ) { productsItem: ProductsItem, actionStr: String ->
                    updateTotal(productsItem, actionStr)
                    GlobalScope.launch {
                        when (actionStr) {
                            "CANCEL" -> {
                                AppDatabase.getDatabase(this@CartActivity).cartDao()
                                    .deleteOneItem(productsItem.id)

                            }
                            "ADD", "REMOVE" -> {
                                AppDatabase.getDatabase(this@CartActivity).cartDao()
                                    .update(productsItem)

                            }
                        }
                    }
                }
            }
        } else {
            showEmpty()
        }
    }

    fun calculateTotal(list: List<ProductsItem>): String {
        for (item in list) {
            totalAmount += item.totalPrice
        }
        return String.format("%,.2f", totalAmount)
    }

    fun updateTotal(item: ProductsItem, actionStr: String) {
        when (actionStr) {
            "CANCEL" -> {
                totalAmount -= item.price!! * item.qty!!
            }
            "ADD" -> {
                totalAmount += item.price!!
            }
            "REMOVE" -> {
                totalAmount -= item.price!!
            }
        }
        if (totalAmount < 0) {
            totalAmount = 0.0
            showEmpty()
        }
        total.text = "Total: $ ${String.format("%,.2f", totalAmount)}"

    }

    override fun showEmpty() {
        GlobalScope.launch(Dispatchers.Main) {
            isShowProgress(false)
            noItem.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

    override fun isShowProgress(showProgress: Boolean) {
        GlobalScope.launch(Dispatchers.Main) {
            if (showProgress) {
                progressDot.visibility = View.VISIBLE
            } else {
                progressDot.visibility = View.GONE
            }
        }
    }
}