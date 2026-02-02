package com.seuapp.pomodoro.utils

object Validacao {

    fun validarEmail(email: String): Boolean {
        return email.isNotBlank() && email.contains("@")
    }

    fun validarSenha(senha: String): Boolean {
        return senha.isNotBlank() && senha.length >= 4
    }

    fun validarNome(nome: String): Boolean {
        return nome.isNotBlank()
    }
}

