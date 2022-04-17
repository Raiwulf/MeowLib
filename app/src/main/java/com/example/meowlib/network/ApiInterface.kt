package com.example.meowlib.network

import com.example.meowlib.model.CatsAPI
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET("breeds")
    fun getData() : Call<List<CatsAPI>>
    @GET("breeds/search")
    fun searchData(@Query("q") q: String):  Call<List<CatsAPI>>

    companion object {

        var BASE_URL = "https://api.thecatapi.com/v1/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }

    }
}