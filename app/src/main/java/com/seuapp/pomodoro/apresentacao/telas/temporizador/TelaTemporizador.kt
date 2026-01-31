package com.seuapp.pomodoro.apresentacao.telas.temporizador

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.seuapp.pomodoro.apresentacao.estado.EstatisticasEstado
import androidx.compose.ui.platform.LocalContext
import com.seuapp.pomodoro.utils.NotificacaoUtil

@Composable
fun TelaTemporizador() {

    var tipoSessao by remember { mutableStateOf(TipoSessao.TRABALHO) }
    var tempoRestante by remember { mutableStateOf(tipoSessao.duracaoSegundos) }
    var rodando by remember { mutableStateOf(false) }
    var mostrarDialogo by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Atualiza tempo quando muda a sessão
    LaunchedEffect(tipoSessao) {
        rodando = false
        tempoRestante = tipoSessao.duracaoSegundos
    }

    // Controle do temporizador
    LaunchedEffect(rodando) {
        while (rodando && tempoRestante > 0) {
            delay(1000L)
            tempoRestante--
        }

        if (tempoRestante == 0 && rodando) {
            rodando = false
            mostrarDialogo = true

            if (tipoSessao == TipoSessao.TRABALHO) {
                EstatisticasEstado.sessoesConcluidas.value++
                EstatisticasEstado.minutosFocados.value += 25
            }
        }
    }

    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = { mostrarDialogo = false },
            confirmButton = {
                Button(onClick = { mostrarDialogo = false }) {
                    Text("OK")
                }
            },
            title = { Text("Sessão concluída 🎉") },
            text = { Text("Você concluiu a sessão de ${tipoSessao.titulo}.") }
        )
        NotificacaoUtil.mostrarNotificacao(
            context = context,
            titulo = "Pomodoro concluído",
            mensagem = "Sessão de ${tipoSessao.titulo} finalizada"
        )

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = tipoSessao.titulo,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = formatarTempo(tempoRestante),
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botões de sessão
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            OutlinedButton(onClick = { tipoSessao = TipoSessao.TRABALHO }) {
                Text("Trabalho")
            }

            OutlinedButton(onClick = { tipoSessao = TipoSessao.PAUSA_CURTA }) {
                Text("Pausa Curta")
            }

            OutlinedButton(onClick = { tipoSessao = TipoSessao.PAUSA_LONGA }) {
                Text("Pausa Longa")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Controles
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

            Button(
                onClick = { rodando = true },
                enabled = !rodando
            ) {
                Text("Iniciar")
            }

            Button(
                onClick = { rodando = false },
                enabled = rodando
            ) {
                Text("Pausar")
            }

            OutlinedButton(onClick = {
                rodando = false
                tempoRestante = tipoSessao.duracaoSegundos
            }) {
                Text("Resetar")
            }
        }
    }
}

fun formatarTempo(segundos: Int): String {
    val minutos = segundos / 60
    val restoSegundos = segundos % 60
    return String.format("%02d:%02d", minutos, restoSegundos)
}
