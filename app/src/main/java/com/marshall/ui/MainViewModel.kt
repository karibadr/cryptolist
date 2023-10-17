package com.marshall.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marshall.data.CryptoCurrency
import com.marshall.data.CryptoRepository
import com.marshall.data.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val cryptoRepository: CryptoRepository) :
    ViewModel() {

    private val _cryptoResults = MutableStateFlow<Results<List<CryptoCurrency>>>(Results.Empty())
    val cryptoResults: Flow<Results<List<CryptoCurrency>>> = _cryptoResults

    fun loadCryptoCurrencies(isRefresh: Boolean) {
        cryptoRepository.getAllCryptoCurrencies(isRefresh).onEach {
            _cryptoResults.value = it
        }.launchIn(viewModelScope)
    }


}