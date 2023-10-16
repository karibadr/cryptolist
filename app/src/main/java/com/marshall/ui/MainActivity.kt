package com.marshall.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.marshall.data.CryptoCurrency
import com.marshall.ui.theme.MarshallTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarshallTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel = hiltViewModel()) {
    val cryptoResult by remember { viewModel.getAllCryptoCurrencies() }.collectAsState(initial = null)
    CryptoListView(cryptoResult)
}

@Composable
fun CryptoListView(result: List<CryptoCurrency>?) {
    result?.let {
        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp)) {
            items(result) { item ->
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
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
                        Text(text = item.price)
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarshallTheme {
        HomeScreen()
    }
}