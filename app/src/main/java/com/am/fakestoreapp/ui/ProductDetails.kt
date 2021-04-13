package com.am.fakestoreapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.am.fakestoreapp.R
import com.am.fakestoreapp.model.ProductsItem
import com.am.fakestoreapp.mvp.details.DetailContract
import com.am.fakestoreapp.mvp.details.DetailsPresenter
import com.am.fakestoreapp.service.ProductService
import com.am.fakestoreapp.utils.INTENT_PRODUCT_ID
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_activity.progressDot
import kotlinx.android.synthetic.main.product_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductDetails : AppCompatActivity(), DetailContract.IView {
    private val presenter = DetailsPresenter(this, ProductService())
    private var productId: Int = 0
    var quantity = 1
    private lateinit var product: ProductsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)
        if (intent != null) {
            productId = intent.getIntExtra(INTENT_PRODUCT_ID, 0)
            presenter.getProductDetails(productId)
        }
        uiInit()
    }

    private fun uiInit() {
        showEmpty(true)
        isShowProgress(true)
        remove.setOnClickListener {
            if (quantity > 1) {
                quantity -= 1
                qty.text = "$quantity"
            }
        }
        add.setOnClickListener {
            if (quantity <= 9) {
                quantity += 1
                qty.text = "$quantity"
            } else
                Toast.makeText(this, "Reached maximum limit", Toast.LENGTH_SHORT).show()
        }
        addToCartBtn.setOnClickListener {
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show()
            presenter.addToCart(this, product.also {
                it.qty = quantity
            })
        }
    }

    override fun updateUI(item: ProductsItem?) {
        isShowProgress(false)
        if (item != null) {
            showEmpty(false)
            product = item
            name.text = item.title
            price.text = "$ ${item.price}"
            category.text = item.category
            description.text = item.description
            Glide.with(this).load(item.image).placeholder(R.drawable.ic_downloading)
                .error(R.drawable.ic_error).into(productImg)

        } else showEmpty(true)
    }


    override fun success(item: ProductsItem?) {
        updateUI(item)
    }

    override fun showEmpty(isShow: Boolean) {
        when (isShow) {
            true -> {
                detailsLayout.visibility = View.GONE
                addToCartBtn.visibility = View.GONE
            }
            false -> {
                detailsLayout.visibility = View.VISIBLE
                addToCartBtn.visibility = View.VISIBLE
            }
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