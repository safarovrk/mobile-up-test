package com.example.mobileuptest.data.repository

import com.example.mobileuptest.data.retrofit.RetrofitClient
import com.example.mobileuptest.domain.models.Cryptocurrency
import com.example.mobileuptest.domain.repository.Repository

class RepositoryImpl : Repository {
    override suspend fun getCryptocurrencies(currency: String): List<Cryptocurrency> {
        return RetrofitClient.serviceAPI.getCryptocurrencies(currency).map {
            return@map Cryptocurrency(
                name = it.name,
                image = it.image,
                price_change_percentage_24h = it.price_change_percentage_24h,
                current_price = it.current_price,
                symbol = it.symbol
            )
        }
    }
}