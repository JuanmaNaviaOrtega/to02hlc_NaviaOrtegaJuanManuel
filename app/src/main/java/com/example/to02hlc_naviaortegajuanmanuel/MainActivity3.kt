package com.example.to02hlc_naviaortegajuanmanuel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CuadradosPerfectosApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuadradosPerfectosApp() {
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = numero1,
            onValueChange = { numero1 = it },
            label = { Text("Introduce el primer número") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = error && numero1.isBlank(),
            supportingText = { if (error && numero1.isBlank()) Text("Ingresa un número") else null }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = numero2,
            onValueChange = { numero2 = it },
            label = { Text("Introduce el segundo número") },keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = error && numero2.isBlank(),
            supportingText = { if (error && numero2.isBlank()) Text("Ingresa un número") else null }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            error = false
            try {
                val n1 = numero1.toInt()
                val n2 = numero2.toInt()
                resultado = calcularCuadradosPerfectos(n1, n2)
            } catch (e: NumberFormatException) {
                error = true
                resultado = "Error: por favor, Ingresa números válidos."
            } catch (e: Exception) {
                error = true
                resultado = "Error: ${e.message}"
            }
        }) {
            Text("Calcular cuadrados perfectos")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (error) {
            Text(resultado, color = MaterialTheme.colorScheme.error)
        } else {
            Text(resultado)
        }
    }
}

fun calcularCuadradosPerfectos(num1: Int, num2: Int): String {
    val inicio = minOf(num1, num2)
    val fin = maxOf(num1, num2)
    val cuadradosPerfectos = mutableListOf<Int>()
    for (i in inicio..fin) {
        val raiz = sqrt(i.toDouble())
        if (raiz == raiz.toInt().toDouble()) {
            cuadradosPerfectos.add(i)
        }
    }
    return if (cuadradosPerfectos.isEmpty()) {
        "No hay cuadrados perfectos en el rango."
    } else {
        "Cuadrados perfectos: ${cuadradosPerfectos.joinToString(", ")}"
    }
}