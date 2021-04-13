package com.am.fakestoreapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Product_Item_Table")
data class ProductsItem(
    @PrimaryKey(autoGenerate = true) var _autoId: Int = 0,

    @ColumnInfo(name = "qty")
    @SerializedName("qty") var qty: Int? = 0,

    @ColumnInfo(name = "totalPrice")
    @SerializedName("totalPrice") var totalPrice: Double = 0.0,

    @ColumnInfo(name = "image")
    @field:SerializedName("image")
    val image: String? = null,

    @ColumnInfo(name = "price")
    @field:SerializedName("price")
    val price: Double? = null,

    @ColumnInfo(name = "description")
    @field:SerializedName("description")
    val description: String? = null,

    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: Int? = null,

    @ColumnInfo(name = "title")
    @field:SerializedName("title")
    val title: String? = null,

    @ColumnInfo(name = "category")
    @field:SerializedName("category")
    val category: String? = null
)
