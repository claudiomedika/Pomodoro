package com.seuapp.pomodoro.dados.rede

import com.seuapp.pomodoro.dados.dto.CadastroRequest
import com.seuapp.pomodoro.dados.dto.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<String>

    @POST("/auth/cadastro")
    suspend fun cadastro(@Body request: CadastroRequest): Response<String>
}

