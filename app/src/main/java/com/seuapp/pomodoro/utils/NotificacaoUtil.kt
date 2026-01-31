package com.seuapp.pomodoro.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.seuapp.pomodoro.R
import android.os.Build

object NotificacaoUtil {

    private const val CANAL_ID = "pomodoro_canal"

    fun criarCanal(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canal = NotificationChannel(
                CANAL_ID,
                "Pomodoro",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notificações do temporizador Pomodoro"
            }

            val manager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(canal)
        }
    }

    fun mostrarNotificacao(
        context: Context,
        titulo: String,
        mensagem: String
    ) {
        val notificacao = NotificationCompat.Builder(context, CANAL_ID)
            .setSmallIcon(R.drawable.ic_timer)
            .setContentTitle(titulo)
            .setContentText(mensagem)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        NotificationManagerCompat
            .from(context)
            .notify(1001, notificacao)
    }
}
