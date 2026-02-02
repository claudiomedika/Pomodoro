package com.seuapp.pomodoro.apresentacao.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seuapp.pomodoro.dados.dto.CadastroRequest
import com.seuapp.pomodoro.dados.dto.LoginRequest
import com.seuapp.pomodoro.dados.rede.RetrofitClient
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    var carregando = mutableStateOf(false)
        private set

    var erro = mutableStateOf<String?>(null)
        private set

    fun setErro(msg: String?) {
        erro.value = msg
    }

    fun login(email: String, senha: String, onSucesso: () -> Unit) {
        viewModelScope.launch {
            carregando.value = true
            erro.value = null

            try {
                val response = RetrofitClient.authApi.login(LoginRequest(email, senha))

                if (response.isSuccessful) {
                    onSucesso()
                } else {
                    erro.value = "Email ou senha inválidos"
                }

            } catch (e: Exception) {
                erro.value = "Erro ao conectar: ${e.message}"
            }

            carregando.value = false
        }
    }

    fun cadastrar(nome: String, email: String, senha: String, onSucesso: () -> Unit) {
        viewModelScope.launch {
            carregando.value = true
            erro.value = null

            try {
                val response = RetrofitClient.authApi.cadastro(CadastroRequest(nome, email, senha))

                if (response.isSuccessful) {
                    onSucesso()
                } else {
                    erro.value = "Erro ao cadastrar (email pode já existir)"
                }

            } catch (e: Exception) {
                erro.value = "Erro ao conectar: ${e.message}"
            }

            carregando.value = false
        }
    }
}
