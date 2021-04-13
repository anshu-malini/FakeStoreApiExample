package com.am.fakestoreapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.am.fakestoreapp.R
import com.am.fakestoreapp.adapter.HomeAdapter
import com.am.fakestoreapp.model.ProductsItem
import com.am.fakestoreapp.mvp.home.HomeContract
import com.am.fakestoreapp.mvp.home.HomePresenter
import com.am.fakestoreapp.room.AppDatabase
import com.am.fakestoreapp.service.ProductService
import com.am.fakestoreapp.utils.INTENT_PRODUCT_ID
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity(), HomeContract.IView {
    private val presenter =
        HomePresenter(this, ProductService())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        initUI()
        reloadData()
        swipeRefreshLayout.setOnRefreshListener {
            reloadData()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun initUI() {
        cartImg.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
            Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun reloadData() {
        isShowProgress(true)
        presenter.getProductList()
    }

    override fun success(list: List<ProductsItem>?) {
        isShowProgress(false)
        if (list != null) {
            noItem.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            recyclerView.apply {
                layoutManager = GridLayoutManager(this@HomeActivity, 2)
                adapter = HomeAdapter(this@HomeActivity, list.sortedBy { it.price }) {
                    startActivity(
                        Intent(
                            this@HomeActivity,
                            ProductDetails::class.java
                        ).apply { putExtra(INTENT_PRODUCT_ID, it.id) })
                }
            }
        } else {
            showEmpty()
        }
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