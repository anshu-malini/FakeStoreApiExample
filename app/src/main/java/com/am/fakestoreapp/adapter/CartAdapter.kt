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
    var productList: MutableList<ProductsItem>,
    val onItemClick: (ProductsItem, String) -> Unit
) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder =
            LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false)
        return ViewHolder(viewHolder) { pos: Int, actionStr: String ->
            onItemClick(productList[pos], actionStr)
        }
    }


    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = productList[position].title
        holder.price.text = "$ ${productList[position].price}"
        holder.cancel.setOnClickListener {
            onItemClick(productList[position], "CANCEL")
            productList.removeAt(position)
            notifyItemRemoved(position)
        }
        var quantity = productList[position].qty
        holder.qtyTV.text = "$quantity"
        holder.add.setOnClickListener {
            if (quantity != null && quantity < 10) {
                quantity += 1
                holder.qtyTV.text = "$quantity"
                onItemClick(productList[position].also {
                    it.qty = quantity
                    it.totalPrice = it.totalPrice + it.price!!
                }, "ADD")
                notifyItemChanged(position)
            }
        }
        holder.remove.setOnClickListener {
            if (quantity != null && quantity >= 2) {
                quantity -= 1
                holder.qtyTV.text = "$quantity"
                onItemClick(productList[position].also {
                    it.qty = quantity
                    it.totalPrice = it.totalPrice - it.price!!
                }, "REMOVE")
                notifyItemChanged(position)
            }
        }
    }

    class ViewHolder(itemView: View, onItemClick: (Int, String) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.cancel.setOnClickListener { onItemClick(adapterPosition, "CANCEL") }
            itemView.add.setOnClickListener { onItemClick(adapterPosition, "ADD") }
            itemView.remove.setOnClickListener { onItemClick(adapterPosition, "REMOVE") }
        }

        val name = itemView.name!!
        val price = itemView.price!!
        val cancel = itemView.cancel!!
        val remove = itemView.remove!!
        val add = itemView.add!!
        val qtyTV = itemView.qtyTV!!

    }
}