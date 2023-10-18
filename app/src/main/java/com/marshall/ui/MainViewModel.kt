package com.marshall.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marshall.data.CryptoCurrency
import com.marshall.data.CryptoRepository
import com.marshall.data.Results
import com.marshall.ui.utils.Currencies
import com.marshall.ui.utils.Currency
import com.marshall.ui.utils.convertToSelectedCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val cryptoRepository: CryptoRepository) :
    ViewModel() {

    private val _selectedCurrency = MutableStateFlow(Currencies.USD)
    val selectedCurrency: StateFlow<Currency> = _selectedCurrency

    private val _cryptoResults = MutableStateFlow<Results<List<CryptoCurrency>>>(Results.Empty())
    val cryptoResults: Flow<Results<List<CryptoCurrency>>> = _cryptoResults

    fun loadCryptoCurrencies(isRefresh: Boolean) {
        cryptoRepository.getAllCryptoCurrencies(isRefresh).onEach { result ->
            if (result is Results.Success) {
                val convertedCryptoList = result.data.map { cryptoCurrency ->
                    val convertedPrice = convertToSelectedCurrency(
                        cryptoCurrency.price.toDouble(),
                        selectedCurrency.value
                    )
                    cryptoCurrency.copy(price = convertedPrice.toString())
                }
                _cryptoResults.value = Results.Success(convertedCryptoList)
            } else {
                _cryptoResults.value = result
            }
        }.launchIn(viewModelScope)
    }

    fun setSelectedCurrency(currency: Currency) {
        _selectedCurrency.value = currency
        loadCryptoCurrencies(false)
    }

}