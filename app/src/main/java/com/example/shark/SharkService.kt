package com.example.shark
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SharkService {
    @GET("api/1.0/deals")
    suspend fun getDeals() : List<Deal>

    @GET("api/1.0/deals")
    fun getDeals(@Query("search")title:String) : List<Deal>

}