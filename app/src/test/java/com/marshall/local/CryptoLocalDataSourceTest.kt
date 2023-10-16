package com.marshall.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.marshall.TestingData.localCryptoCurrencies
import com.marshall.data.local.CryptoLocalDataSource
import com.marshall.data.local.db.CryptoDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
 class CryptoLocalDataSourceTest {

    private lateinit var localDataSourceTest: CryptoLocalDataSource
    private lateinit var database: CryptoDatabase

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, CryptoDatabase::class.java).build()
        localDataSourceTest = CryptoLocalDataSource(database)
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun `insert crypto currencies`() = runBlocking {
        localDataSourceTest.updateCryptos(localCryptoCurrencies())
        val result = localDataSourceTest.getCryptos().first()
        assertThat(result).isEqualTo(localCryptoCurrencies())
    }

    @Test
    fun `overwrite crypto currencies`() = runBlocking {
        localDataSourceTest.updateCryptos(localCryptoCurrencies())
        val result = localDataSourceTest.getCryptos().first().count()
        assertThat(result).isEqualTo(localCryptoCurrencies().size)
    }

}