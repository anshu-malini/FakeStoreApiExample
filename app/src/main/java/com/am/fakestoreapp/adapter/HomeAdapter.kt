package com.am.fakestoreapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.am.fakestoreapp.R
import com.am.fakestoreapp.model.ProductsItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.product_list_item.view.*

class HomeAdapter(
    private val context: Context,
    var productList: List<ProductsItem>,
    val onItemClick: (ProductsItem) -> Unit
) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder =
            LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false)
        return ViewHolder(viewHolder) {
            onItemClick(productList[it])
        }
    }


    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = productList[position].title
        holder.price.text = "$ ${productList[position].price}"
        Glide.with(context).load(productList[position].image).placeholder(R.drawable.ic_downloading)
            .error(R.drawable.ic_error)
            .into(holder.itemImg)
        holder.itemView.setOnClickListener { onItemClick(productList[position]) }
    }

    class ViewHolder(itemView: View, onItemClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onItemClick(adapterPosition) }
        }

        val name = itemView.name!!
        val price = itemView.price!!
        val itemImg = itemView.itemImg!!
    }
}