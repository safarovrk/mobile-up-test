package com.example.mobileuptest.presentation.cryptocurrencies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileuptest.domain.usecases.GetCryptocurrenciesUseCase
import kotlinx.coroutines.launch

class CryptocurrenciesViewModel: ViewModel() {

    val cryptocurrenciesModel: MutableLiveData<CryptocurrenciesModel> = MutableLiveData()
    private val getCryptocurrenciesUseCase = GetCryptocurrenciesUseCase()
    val exception: MutableLiveData<Boolean> = MutableLiveData()

    val checkedChipValue: MutableLiveData<String> = MutableLiveData()

    init {
        cryptocurrenciesModel.value = CryptocurrenciesModel(listOf())
    }

    fun loadData(currency: String) {
        viewModelScope.launch {
            try {
                cryptocurrenciesModel.value =
                    CryptocurrenciesModel(getCryptocurrenciesUseCase.invoke(currency))
            } catch (e: Exception) { exception.value = true }
        }
    }
}