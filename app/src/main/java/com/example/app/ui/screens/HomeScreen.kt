package com.seuprojeto.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Card Principal - Sobra do mês
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Janeiro", style = MaterialTheme.typography.titleMedium)

                Text(
                    text = "Sobra do mês",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "R$ 3.333,33",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Receitas e Despesas
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "Receitas",
                            color = Green,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(text = "R$ 4.000,00", color = Green)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Despesas",
                            color = Red,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(text = "R$ 666,66", color = Red)
                    }
                }
            }
        }

        // Card de Resumo Mensal - Despesas do Mês
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Para expandir proporcionalmente
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Despesas do mês",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Gráfico Placeholder (Canvas)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    // Desenhando o gráfico de barras
                    BarChart()
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Detalhes de categorias
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "Salário: R$ 4.000,00", color = Color(0xFFFFEB3B)) // Amarelo
                    Text(text = "Poupança: R$ 1.400,00", color = Color(0xFF00BCD4)) // Azul
                    Text(text = "Cartão: R$ 666,66", color = Color(0xFFF44336)) // Vermelho
                }
            }
        }
    }
}

@Composable
fun BarChart() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        // Tamanho do gráfico
        val barWidth = size.width / 4
        val maxHeight = size.height

        // Desenhando as barras
        drawRect(
            color = Green,
            topLeft = androidx.compose.ui.geometry.Offset(x = 0f, y = maxHeight - 150f),
            size = androidx.compose.ui.geometry.Size(width = barWidth, height = 150f)
        )

        drawRect(
            color = Red,
            topLeft = androidx.compose.ui.geometry.Offset(x = barWidth + 10f, y = maxHeight - 90f),
            size = androidx.compose.ui.geometry.Size(width = barWidth, height = 90f)
        )

        drawRect(
            color = Color(0xFFFFEB3B),
            topLeft = androidx.compose.ui.geometry.Offset(x = 2 * barWidth + 20f, y = maxHeight - 200f),
            size = androidx.compose.ui.geometry.Size(width = barWidth, height = 200f)
        )

        drawRect(
            color = Color(0xFF00BCD4),
            topLeft = androidx.compose.ui.geometry.Offset(x = 3 * barWidth + 30f, y = maxHeight - 120f),
            size = androidx.compose.ui.geometry.Size(width = barWidth, height = 120f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}
