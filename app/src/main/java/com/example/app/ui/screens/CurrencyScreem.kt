package com.seuprojeto.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class CurrencyTest(
    val code: String,  // Código da moeda (USD, EUR, etc.)
    val name: String,  // Nome da moeda (Dólar, Euro, etc.)
    val valor: Double   // Cotação da moeda
)

@Composable
fun CurrencyScreen() {
    // Lista de moedas fixas (cotações)
    val availableCurrencies = listOf(
        CurrencyTest("USD", "Dólar Americano", 5.25),
        CurrencyTest("EUR", "Euro", 6.10),
        CurrencyTest("GBP", "Libra", 7.30)
    )

    // Lista de moedas que o usuário adicionou
    var userCurrencies by remember { mutableStateOf(mutableListOf<CurrencyTest>()) }
    var searchQuery by remember { mutableStateOf("") }
    var newCurrencyName by remember { mutableStateOf("") }
    var newCurrencyCode by remember { mutableStateOf("") }
    var newCurrencyValue by remember { mutableStateOf<Double?>(null) } // Valor da moeda exibido após seleção

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campo de busca
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Buscar moeda...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Campos para adicionar nova moeda
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = newCurrencyName,
                onValueChange = { newCurrencyName = it },
                label = { Text("Nome da Moeda") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = newCurrencyCode,
                onValueChange = { newCurrencyCode = it },
                label = { Text("Código da Moeda") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))

            // Botão de Adicionar
            IconButton(onClick = {
                if (newCurrencyName.isNotEmpty() && newCurrencyCode.isNotEmpty()) {
                    val currency = availableCurrencies.find { it.code == newCurrencyCode }
                    if (currency != null) {
                        newCurrencyValue = currency.valor // Exibe o valor da moeda selecionada
                        userCurrencies.add(currency) // Adiciona a moeda à lista do usuário
                        newCurrencyName = ""
                        newCurrencyCode = ""
                    }
                }
            }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Moeda")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Exibir valor da moeda após seleção
        if (newCurrencyValue != null) {
            Text("Valor selecionado: R$ ${newCurrencyValue}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de moedas filtradas (somente as moedas adicionadas pelo usuário)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(userCurrencies.filter {
                it.name.contains(searchQuery, ignoreCase = true) || it.code.contains(searchQuery, ignoreCase = true)
            }) { currency ->
                CurrencyCard(currency, onDelete = {
                    // Remove a moeda da lista do usuário
                    userCurrencies = userCurrencies.filter { it != currency }.toMutableList()
                })
            }
        }
    }
}

@Composable
fun CurrencyCard(currency: CurrencyTest, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = currency.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = "${currency.code} - R$ ${currency.valor}", style = MaterialTheme.typography.bodySmall)
            }

            // Botão de remoção
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Remover Moeda", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}
