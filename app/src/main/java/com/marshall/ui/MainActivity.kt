package com.marshall.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.marshall.R
import com.marshall.data.CryptoCurrency
import com.marshall.data.Results
import com.marshall.ui.utils.Currencies
import com.marshall.ui.utils.Currency
import com.marshall.ui.theme.MarshallTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarshallTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "\uD83E\uDE99 Crypto currencies",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 22.sp,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Left
                                    )
                                }
                            )
                        }
                    ) { innerPadding ->
                        Column(
                            modifier = Modifier
                                .padding(innerPadding),
                        ) {
                            CryptoListView()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CryptoListView(viewModel: MainViewModel = hiltViewModel()) {
    val result by remember { viewModel.cryptoResults }.collectAsState(initial = null)
    val selectedCurrency by remember { viewModel.selectedCurrency }.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadCryptoCurrencies(isRefresh = false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        // Currency selection chips
        CurrencyChips(
            selectedCurrency = selectedCurrency,
            onCurrencySelected = { currency ->
                viewModel.setSelectedCurrency(currency)
            }
        )
        result?.let {
            when (it) {
                is Results.Success -> {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(it.data) { item ->
                            CryptoListItem(item)
                        }
                    }
                }

                is Results.Loading -> {
                    LoadingScreen()
                }

                is Results.Error -> {
                    ErrorScreen()
                }

                is Results.Empty -> {
                    EmptyScreen()
                }
            }
        }
    }
}

@Composable
fun CryptoListItem(item: CryptoCurrency) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = item.symbol,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = String.format("%.3f", item.price.toDouble()),
                fontWeight = FontWeight.Bold
            )
        }
    }
    Spacer(modifier = Modifier.height(14.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyChips(
    selectedCurrency: Currency,
    onCurrencySelected: (Currency) -> Unit
) {
    val currencies = listOf(Currencies.INR, Currencies.USD, Currencies.SEK)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (currency in currencies) {
            FilterChip(
                label = { Text(currency.code) },
                selected = currency == selectedCurrency,
                onClick = {
                    onCurrencySelected(currency)
                }
            )
        }
    }
}

@Composable
fun EmptyScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.nothing_to_show),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
    }
}


@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.something_crazy_just_happened),
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarshallTheme {

    }
}
