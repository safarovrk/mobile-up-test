package com.example.mobileuptest.data.retrofit

import com.example.mobileuptest.data.dto.CryptocurrencyResponseValue
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI  {
    @GET("coins/markets")
    suspend fun getCryptocurrencies(@Query("vs_currency") vs_currency: String): List<CryptocurrencyResponseValue>
}