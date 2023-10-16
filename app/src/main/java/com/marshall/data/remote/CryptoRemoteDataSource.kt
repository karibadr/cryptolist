package com.marshall.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

class CryptoRemoteDataSource @Inject constructor(
    private val serviceURL: HttpUrl
) {

    private val apiService by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(serviceURL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        retrofit.create(APIService::class.java)
    }

    suspend fun getAllCryptoCurrencies() =
        apiService.getAllCryptoCurrencies()

}


interface APIService {
    @GET("sapi/v1/tickers/24hr")
    suspend fun getAllCryptoCurrencies(): List<RemoteCryptoCurrency>
}