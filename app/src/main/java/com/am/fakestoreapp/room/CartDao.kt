package com.am.fakestoreapp.room

import androidx.room.*
import com.am.fakestoreapp.model.ProductsItem

@Dao
abstract class CartDao {
    @Query("SELECT * FROM Product_Item_Table ORDER BY price ASC")
    abstract suspend fun getAllCartItem(): List<ProductsItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(item: ProductsItem)

    @Query("DELETE FROM Product_Item_Table")
    abstract suspend fun deleteAll()

    @Query("DELETE FROM Product_Item_Table where id=:productId ")
    abstract suspend fun deleteOneItem(productId: Int?)

    @Query("Select exists(Select id from Product_Item_Table where id=:productId)")
    abstract fun exists(productId: Int?): Boolean

    @Update
    abstract fun update(productsItem: ProductsItem)
}