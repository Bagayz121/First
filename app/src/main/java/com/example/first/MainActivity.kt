package com.example.first

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.first.ui.theme.FirstTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RhombusScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun RhombusScreen(modifier: Modifier = Modifier) {
    var variant by remember { mutableStateOf("") }
    var value1 by remember { mutableStateOf("") }
    var value2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    // Динамические подписи полей в зависимости от выбранного варианта
    val label1 = when (variant) {
        "1" -> "Сторона a"
        "2" -> "Диагональ d1"
        else -> "Значение 1"
    }
    val label2 = when (variant) {
        "1" -> "Высота h"
        "2" -> "Диагональ d2"
        else -> "Значение 2"
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Площадь ромба",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = variant,
            onValueChange = { variant = it },
            label = { Text("Номер варианта (1 или 2)") },
            singleLine = true
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = value1,
            onValueChange = { value1 = it },
            label = { Text(label1) },
            singleLine = true
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = value2,
            onValueChange = { value2 = it },
            label = { Text(label2) },
            singleLine = true
        )
        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            val v1 = value1.toDoubleOrNull()
            val v2 = value2.toDoubleOrNull()
            val variantNum = variant.toIntOrNull()

            // Проверка корректности ввода
            if (v1 == null || v2 == null) {
                result = "Введите корректные числа"
                return@Button
            }
            if (v1 <= 0.0 || v2 <= 0.0) {
                result = "Значения должны быть больше нуля"
                return@Button
            }

            // Основной выбор формулы через when
            result = when (variantNum) {
                1 -> "S = ${v1 * v2}"          // S = a * h
                2 -> "S = ${(v1 * v2) / 2}"    // S = (d1 * d2) / 2
                else -> "Неверный номер варианта"
            }
        }) {
            Text("Вычислить")
        }

        Spacer(Modifier.height(16.dp))
        Text(text = "Результат: $result")
    }
}

@Preview(showBackground = true)
@Composable
fun RhombusScreenPreview() {
    FirstTheme {
        RhombusScreen()
    }
}