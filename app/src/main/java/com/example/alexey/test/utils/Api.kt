package com.example.alexey.test.utils

import com.example.alexey.test.models.Stock
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor






interface Api {
    @GET("stocks.json")
    fun getWetherList(): Observable<Stock>


    companion object Factory {
        fun create(): Api {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://phisix-api3.appspot.com/")
                .client(client)
                .build()

            return retrofit.create(Api::class.java)
        }
    }
}