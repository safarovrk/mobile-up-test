package com.example.mobileuptest.data.repository

import com.example.mobileuptest.data.retrofit.RetrofitClient
import com.example.mobileuptest.domain.models.CryptoInfo
import com.example.mobileuptest.domain.models.Cryptocurrency
import com.example.mobileuptest.domain.repository.Repository

class RepositoryImpl : Repository {
    override suspend fun getCryptocurrencies(currency: String): List<Cryptocurrency> {
        return RetrofitClient.serviceAPI.getCryptocurrencies(currency).map {
            return@map Cryptocurrency(
                id = it.id,
                name = it.name,
                image = it.image,
                price_change_percentage_24h = it.price_change_percentage_24h,
                current_price = it.current_price,
                symbol = it.symbol
            )
        }
    }

    override suspend fun getCryptoInfo(id: String): CryptoInfo {
        val response = RetrofitClient.serviceAPI.getCryptoInfo(id)
        return CryptoInfo(
            image = CryptoInfo.Image(response.image.large),
            description = CryptoInfo.Description(response.description.en),
            categories = response.categories
        )
    }
}