package com.example.mobileuptest.domain.usecases

import com.example.mobileuptest.data.repository.RepositoryImpl
import com.example.mobileuptest.domain.models.Cryptocurrency

class GetCryptocurrenciesUseCase {
    suspend fun invoke(currency: String): List<Cryptocurrency> {

        return RepositoryImpl().getCryptocurrencies(currency)

    }
}