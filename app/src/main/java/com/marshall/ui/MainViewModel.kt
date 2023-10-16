package com.marshall.ui

import androidx.lifecycle.ViewModel
import com.marshall.data.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val cryptoRepository: CryptoRepository) :
    ViewModel() {
    fun getAllCryptoCurrencies() =
        cryptoRepository.getAllCryptoCurrencies()

}