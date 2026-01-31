package com.seuapp.pomodoro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.seuapp.pomodoro.apresentacao.telas.estatisticas.TelaEstatisticas
import com.seuapp.pomodoro.apresentacao.telas.login.TelaCadastro
import com.seuapp.pomodoro.apresentacao.telas.login.TelaLogin
import com.seuapp.pomodoro.apresentacao.telas.metas.TelaMetas
import com.seuapp.pomodoro.apresentacao.telas.temporizador.TelaTemporizador
import com.seuapp.pomodoro.utils.NotificacaoUtil

/**
 * Activity principal do aplicativo Pomodoro.
 * Responsável apenas por:
 * - Definir o layout base (Scaffold)
 * - Controlar navegação simples por estado
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificacaoUtil.criarCanal(this)
        setContent {
            AppPomodoro()
        }
    }
}

/**
 * Telas disponíveis no aplicativo.
 * Enum simples para controle de navegação sem dependências externas.
 */
enum class TelaAtual {
    LOGIN,
    CADASTRO,
    TEMPORIZADOR,
    ESTATISTICAS,
    METAS
}

@Composable
fun AppPomodoro() {

    var usuarioLogado by remember { mutableStateOf(false) }
    var telaAtual by remember { mutableStateOf(TelaAtual.LOGIN) }

    if (!usuarioLogado) {

        when (telaAtual) {

            TelaAtual.LOGIN -> TelaLogin(
                onLoginSucesso = {
                    usuarioLogado = true
                    telaAtual = TelaAtual.TEMPORIZADOR
                },
                onCadastrarClick = {
                    telaAtual = TelaAtual.CADASTRO
                }
            )

            TelaAtual.CADASTRO -> TelaCadastro(
                onVoltarLogin = {
                    telaAtual = TelaAtual.LOGIN
                }
            )

            else -> {}
        }

    } else {

        // A PARTIR DAQUI É O APP DE VERDADE
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BarraNavegacao(
                    telaAtual = telaAtual,
                    onTelaSelecionada = { telaAtual = it }
                )
            }
        ) { paddingValues ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (telaAtual) {
                    TelaAtual.TEMPORIZADOR -> TelaTemporizador()
                    TelaAtual.ESTATISTICAS -> TelaEstatisticas()
                    TelaAtual.METAS -> TelaMetas()
                    else -> {}
                }
            }
        }
    }
}


/**
 * Barra de navegação inferior do aplicativo.
 */
@Composable
fun BarraNavegacao(
    telaAtual: TelaAtual,
    onTelaSelecionada: (TelaAtual) -> Unit
) {
    NavigationBar {

        NavigationBarItem(
            selected = telaAtual == TelaAtual.TEMPORIZADOR,
            onClick = { onTelaSelecionada(TelaAtual.TEMPORIZADOR) },
            label = { Text("Timer") },
            icon = {}
        )

        NavigationBarItem(
            selected = telaAtual == TelaAtual.ESTATISTICAS,
            onClick = { onTelaSelecionada(TelaAtual.ESTATISTICAS) },
            label = { Text("Estatísticas") },
            icon = {}
        )

        NavigationBarItem(
            selected = telaAtual == TelaAtual.METAS,
            onClick = { onTelaSelecionada(TelaAtual.METAS) },
            label = { Text("Metas") },
            icon = {}
        )
    }
}
