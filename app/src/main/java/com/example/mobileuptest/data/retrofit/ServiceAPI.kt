package com.example.mobileuptest.data.retrofit

import com.example.mobileuptest.data.dto.CryptoInfoResponseValue
import com.example.mobileuptest.data.dto.CryptocurrencyResponseValue
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceAPI  {
    @GET("coins/markets")
    suspend fun getCryptocurrencies(@Query("vs_currency") vs_currency: String): List<CryptocurrencyResponseValue>

    @GET("coins/{id}?localization=false&tickers=false&market_data=false&community_data=false&developer_data=false&sparkline=false")
    suspend fun getCryptoInfo(@Path("id") id: String): CryptoInfoResponseValue
}