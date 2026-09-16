package com.mrh.calculadoradam.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrh.calculadoradam.data.CalculatorButtonType
import com.mrh.calculadoradam.data.CalculatorChar
import com.mrh.calculadoradam.ui.component.NumberPad
import com.mrh.calculadoradam.ui.theme.RobotoFontFamily

private fun calcular(op1: String, operacion: String, op2: String): String {
    val n1 = op1.replace(",", ".").toDoubleOrNull() ?: return "Error"
    val n2 = op2.replace(",", ".").toDoubleOrNull() ?: return "Error"

    val resultado = when (operacion) {
        "+" -> n1 + n2
        "-" -> n1 - n2
        "×" -> n1 * n2
        "÷" -> if (n2 == 0.0) return "Error" else n1 / n2
        else -> return "Error"
    }

    val texto = if (resultado == resultado.toLong().toDouble()) {
        resultado.toLong().toString()
    } else {
        resultado.toString()
    }
    return texto.replace(".", ",")
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var operador1 by remember { mutableStateOf("") }
    var operacion by remember { mutableStateOf("") }
    var operador2 by remember { mutableStateOf("") }

    fun onBotonPulsado(numero: CalculatorChar) {
        when (numero.type) {
            CalculatorButtonType.NUMBER -> {
                if (operacion.isEmpty()) {
                    operador1 += numero.char
                } else {
                    operador2 += numero.char
                }
            }

            CalculatorButtonType.OPERATOR -> {
                when {
                    operador1.isEmpty() -> Unit
                    operador2.isEmpty() -> operacion = numero.char
                    else -> {
                        // Encadena: si ya hay una operacion completa, se resuelve
                        // y el resultado pasa a ser el primer operando de la siguiente.
                        operador1 = calcular(operador1, operacion, operador2)
                        operacion = numero.char
                        operador2 = ""
                    }
                }
            }

            CalculatorButtonType.EQUALS -> {
                if (operador1.isNotEmpty() && operacion.isNotEmpty() && operador2.isNotEmpty()) {
                    operador1 = calcular(operador1, operacion, operador2)
                    operacion = ""
                    operador2 = ""
                }
            }

            CalculatorButtonType.DELETE -> {
                if (numero.char == "AC") {
                    operador1 = ""
                    operacion = ""
                    operador2 = ""
                } else {
                    when {
                        operador2.isNotEmpty() -> operador2 = operador2.dropLast(1)
                        operacion.isNotEmpty() -> operacion = ""
                        else -> operador1 = operador1.dropLast(1)
                    }
                }
            }

            CalculatorButtonType.PERCENT -> {
                val objetivo = operador2.ifEmpty { operador1 }
                val valor = objetivo.replace(",", ".").toDoubleOrNull()
                if (valor != null) {
                    val resultado = (valor / 100).toString().replace(".", ",")
                    if (operador2.isNotEmpty()) operador2 = resultado else operador1 = resultado
                }
            }

            CalculatorButtonType.BRACKETS -> Unit
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = "$operador1 $operacion $operador2",
                fontFamily = RobotoFontFamily,
                fontSize = 42.sp,
                textAlign = TextAlign.End,
                lineHeight = 40.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        NumberPad(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer),
            onClick = ::onBotonPulsado
        )
    }
}