package com.am.fakestoreapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.am.fakestoreapp.R
import com.am.fakestoreapp.model.ProductsItem
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.product_list_item.view.name
import kotlinx.android.synthetic.main.product_list_item.view.price

class CartAdapter(
    private val context: Context,
    var productList: List<ProductsItem>,
    val onItemClick: (ProductsItem, Int) -> Unit
) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder =
            LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false)
        return ViewHolder(viewHolder) {
            onItemClick(productList[it], it)
        }
    }


    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = productList[position].title
        holder.price.text = "$ ${productList[position].price}"
        holder.cancel.setOnClickListener { onItemClick(productList[position], position) }
        var qty = productList[position].qty
        holder.add.setOnClickListener {
            if (qty != null && qty < 10) {
                qty + 1
            }
        }
        holder.remove.setOnClickListener {
            if (qty != null && qty >= 2) {
                qty - 1
            }
        }
    }

    class ViewHolder(itemView: View, onItemClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onItemClick(adapterPosition) }
        }

        val name = itemView.name!!
        val price = itemView.price!!
        val cancel = itemView.cancel!!
        val remove = itemView.remove!!
        val add = itemView.add!!
        val qty = itemView.qty!!
    }
}