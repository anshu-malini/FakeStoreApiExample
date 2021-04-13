package com.am.fakestoreapp.room

import androidx.room.*
import com.am.fakestoreapp.model.ProductsItem

@Dao
interface CartDao {
    @Query("SELECT * FROM Product_Item_Table ORDER BY price ASC")
    fun getAllCartItem(): List<ProductsItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: ProductsItem)

    @Query("DELETE FROM Product_Item_Table")
    fun deleteAll()

    @Query("DELETE FROM Product_Item_Table where id=:productId ")
    fun deleteOneItem(productId: Int?)

    @Query("Select exists(Select id from Product_Item_Table where id=:productId)")
    fun exists(productId: Int?): Boolean

    @Update
    fun update(productsItem: ProductsItem)
}