package com.seuapp.pomodoro.apresentacao.telas.estatisticas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seuapp.pomodoro.apresentacao.estado.EstatisticasEstado

@Composable
fun TelaEstatisticas() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Estatísticas",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Sessões concluídas: ${EstatisticasEstado.sessoesConcluidas.value}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Minutos focados: ${EstatisticasEstado.minutosFocados.value}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
