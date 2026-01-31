package com.seuapp.pomodoro.apresentacao.telas.temporizador

enum class TipoSessao(val duracaoSegundos: Int, val titulo: String) {
    TRABALHO(25 * 60, "Trabalho"),
    PAUSA_CURTA(5 * 60, "Pausa Curta"),
    PAUSA_LONGA(15 * 60, "Pausa Longa")
}