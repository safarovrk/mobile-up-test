package com.example.mobileuptest.domain.repository

import com.example.mobileuptest.domain.models.CryptoInfo
import com.example.mobileuptest.domain.models.Cryptocurrency

interface Repository {
    suspend fun getCryptocurrencies(currency: String): List<Cryptocurrency>
    suspend fun getCryptoInfo(id: String): CryptoInfo
}