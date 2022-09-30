package com.example.mobileuptest.presentation.cryptoinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileuptest.domain.usecases.GetCryptoInfoUseCase
import kotlinx.coroutines.launch

class CryptoInfoViewModel: ViewModel() {

    val cryptoInfoModel: MutableLiveData<CryptoInfoModel> = MutableLiveData()
    private val getCryptoInfoUseCase = GetCryptoInfoUseCase()
    val exception: MutableLiveData<Boolean> = MutableLiveData()
    val nameData: MutableLiveData<String> = MutableLiveData()
    val idData: MutableLiveData<String> = MutableLiveData()

    fun loadData(id: String) {
        viewModelScope.launch {
            try {
                cryptoInfoModel.value =
                    CryptoInfoModel(getCryptoInfoUseCase.invoke(id))
            } catch (e: Exception) { exception.value = true }
        }
    }
}