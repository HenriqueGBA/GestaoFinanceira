package com.seuprojeto.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.app.model.Transaction


// Função para compartilhar uma transação
fun shareTransaction(context: Context, transaction: Transaction) {
    val shareText = """
        Transação: ${transaction.title}
        Descrição: ${transaction.description}
        Valor: R$ ${transaction.amount}
        Tipo: ${if (transaction.isPositive) "Entrada" else "Saída"}
    """.trimIndent()

    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, "Compartilhar Transação")
    context.startActivity(shareIntent)
}

@Composable
fun HistoryScreen() {

    val context = LocalContext.current // Obter o contexto para o compartilhamento

    // Dados estáticos para simulação
    val transactions = mapOf(
        "Hoje" to listOf(
            Transaction("Título 1", "Categoria | Banco", 100.0, true),
            Transaction("Título 2", "Categoria | Banco", 200.0, false)
        ),
        "Quarta, 05" to listOf(
            Transaction("Título 3", "Categoria | Banco", 300.0, true),
            Transaction("Título 4", "Categoria | Banco", 400.0, false)
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        transactions.forEach { (date, transactionList) ->
            item {
                Text(
                    text = date,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(transactionList) { transaction ->
                TransactionCard(transaction, context)
            }
        }
    }
}

@Composable
fun TransactionCard(transaction: Transaction, context: Context) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícone da transação
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Black, CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Informações da transação
            Column(modifier = Modifier.weight(1f)) {
                Text(text = transaction.title, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = transaction.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Valor e botão de compartilhamento
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "R$ ${transaction.amount}",
                    style = MaterialTheme.typography.bodyMedium
                )

                IconButton(onClick = { shareTransaction(context, transaction) }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Compartilhar",
                        tint = Color.Blue
                    )
                }
            }
        }
    }
}
