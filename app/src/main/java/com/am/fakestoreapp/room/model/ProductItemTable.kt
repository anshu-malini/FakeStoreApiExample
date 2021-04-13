package com.am.fakestoreapp.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Product_Item_Table")
data class ProductItemTable(
    @PrimaryKey(autoGenerate = true) var _autoId: Int = 0,
    @SerializedName("image") var image: String? = null,
    @SerializedName("price") var price: Double? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("category") var category: String? = null
) {
}