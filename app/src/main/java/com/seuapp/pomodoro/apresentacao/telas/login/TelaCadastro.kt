package com.seuapp.pomodoro.apresentacao.telas.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.seuapp.pomodoro.apresentacao.viewmodel.AuthViewModel
import com.seuapp.pomodoro.utils.Validacao

@Composable
fun TelaCadastro(
    onVoltarLogin: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    val carregando = authViewModel.carregando.value
    val erro = authViewModel.erro.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Cadastro", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        if (erro != null) {
            Text(erro, color = MaterialTheme.colorScheme.error)
            Spacer(Modifier.height(10.dp))
        }

        Button(
            onClick = {
                if (!Validacao.validarNome(nome)) {
                    authViewModel.setErro("Digite o nome")
                    return@Button
                }

                if (!Validacao.validarEmail(email)) {
                    authViewModel.setErro("Digite um email válido")
                    return@Button
                }

                if (!Validacao.validarSenha(senha)) {
                    authViewModel.setErro("Senha inválida (mínimo 4 caracteres)")
                    return@Button
                }

                authViewModel.cadastrar(nome, email, senha) {
                    onVoltarLogin()
                }
            },
            enabled = !carregando,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (carregando) "Cadastrando..." else "Cadastrar")
        }

    }
}
