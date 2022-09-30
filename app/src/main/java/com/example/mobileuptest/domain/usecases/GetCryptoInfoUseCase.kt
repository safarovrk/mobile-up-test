package com.example.mobileuptest.domain.usecases

import com.example.mobileuptest.data.repository.RepositoryImpl
import com.example.mobileuptest.domain.models.CryptoInfo

class GetCryptoInfoUseCase {
    suspend fun invoke(id: String): CryptoInfo {

        return RepositoryImpl().getCryptoInfo(id)

    }
}