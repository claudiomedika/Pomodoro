package com.seuapp.pomodoro.apresentacao.telas.metas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TelaMetas() {

    var minutosPorDia by remember { mutableStateOf("") }
    var sessoesPorDia by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Metas de Estudo",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = minutosPorDia,
            onValueChange = { minutosPorDia = it },
            label = { Text("Minutos por dia") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = sessoesPorDia,
            onValueChange = { sessoesPorDia = it },
            label = { Text("Sessões por dia") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Meta")
        }
    }
}
