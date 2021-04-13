package com.am.fakestoreapp.room

import android.content.Context
import androidx.room.Room

class DatabaseClient(var context: Context) {

//    private var mInstance: DatabaseClient? = null
//    private var appDatabase: AppDatabase? = null
//
//    private fun DatabaseClient(mContext: Context) {
//        appDatabase = Room.databaseBuilder(mContext, AppDatabase::class.java, "product_cart_db").build()
//    }
//
//    @Synchronized
//    fun getInstance(mContext: Context): DatabaseClient? {
//        if (mInstance == null) {
//            mInstance = DatabaseClient(mContext)
//        }
//        return mInstance
//    }
//
//    fun getAppDatabase(): AppDatabase? {
//        return appDatabase
//    }
}