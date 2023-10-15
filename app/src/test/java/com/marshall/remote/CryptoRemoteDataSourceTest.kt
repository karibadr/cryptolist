package com.marshall.remote

import com.marshall.TestingData
import com.google.common.truth.Truth.assertThat
import com.marshall.data.remote.CryptoRemoteDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.nio.charset.Charset

internal class CryptoRemoteDataSourceTest {

    private lateinit var testClass: CryptoRemoteDataSource
    private var mockServer = MockWebServer()

    @Before
    fun setUp() {
        mockServer.start()
        testClass = CryptoRemoteDataSource(mockServer.url("/"))
    }

    @After
    fun tearDown() {
        mockServer.shutdown()

    }

    @Test
    fun `fetch and parse crypto currencies`() = runBlocking {
        mockServer.enqueue(
            MockResponse().setBody(
                readJsonFileFromResources()
            )
        )

        val result = testClass.getAllCryptoCurrencies()
        assertThat(result).isEqualTo(TestingData.remoteCryptoCurrencies())
    }

    private fun readJsonFileFromResources(): String {
        val classLoader = javaClass.classLoader
        val inputStream = classLoader?.getResourceAsStream("response.json")
        return inputStream?.readBytes()?.toString(Charset.defaultCharset()) ?: ""
    }
}