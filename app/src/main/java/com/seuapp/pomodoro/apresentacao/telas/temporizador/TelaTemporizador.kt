package com.seuapp.pomodoro.apresentacao.telas.temporizador

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun TelaTemporizador() {

    val tempoInicial = 25 * 60 // 25 minutos em segundos

    var tempoRestante by remember { mutableStateOf(tempoInicial) }
    var rodando by remember { mutableStateOf(false) }

    // Efeito que controla o timer
    LaunchedEffect(rodando) {
        while (rodando && tempoRestante > 0) {
            delay(1000L)
            tempoRestante--
        }
        if (tempoRestante == 0) {
            rodando = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Temporizador Pomodoro",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = formatarTempo(tempoRestante),
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Button(onClick = { rodando = true }) {
                Text("Iniciar")
            }

            Button(onClick = { rodando = false }) {
                Text("Pausar")
            }

            OutlinedButton(onClick = {
                rodando = false
                tempoRestante = tempoInicial
            }) {
                Text("Resetar")
            }
        }
    }
}

/**
 * Converte segundos para o formato MM:SS
 */
fun formatarTempo(segundos: Int): String {
    val minutos = segundos / 60
    val restoSegundos = segundos % 60
    return String.format("%02d:%02d", minutos, restoSegundos)
}
