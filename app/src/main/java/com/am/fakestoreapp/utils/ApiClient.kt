package com.am.fakestoreapp.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class ApiClient {

    companion object Factory {
        protected const val Accept = "Accept"
        protected const val ContentType = "Content-Type"
        protected const val JsonMediaType = "application/json"
        private const val Authorization = "Authorization"

        public fun createApi(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(MAIN_API_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(getLoggingGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        private fun getClient(): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.connectTimeout(30, TimeUnit.SECONDS)
            httpClient.readTimeout(30, TimeUnit.SECONDS)
            httpClient.writeTimeout(30, TimeUnit.SECONDS)
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder =
                    original.newBuilder().method(original.method(), original.body())
                requestBuilder.addHeader(
                    Accept,
                    JsonMediaType
                )
                requestBuilder.addHeader(
                    ContentType,
                    JsonMediaType
                )

                val request = requestBuilder.build()
                chain.proceed(request)
            }

            return httpClient.build()
        }

        private fun getLoggingGson(): Gson {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            return GsonBuilder().setLenient().create()
        }

    }
}