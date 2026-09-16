package com.mrh.calculadoradam.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrh.calculadoradam.data.CalculatorButtonType
import com.mrh.calculadoradam.data.CalculatorChar

@Composable
fun NumberPad(modifier: Modifier = Modifier, onClick: (CalculatorChar) -> Unit) {
    val numList = numeros()
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(numList.toList()) { numero ->
            CalculatorButton(
                symbol = numero.char,
                onClick = { onClick(numero) }
            )
        }
    }
}


fun numeros(): Set<CalculatorChar> {
    return linkedSetOf(
        // Fila 1
        CalculatorChar("AC", CalculatorButtonType.DELETE),
        CalculatorChar("()", CalculatorButtonType.BRACKETS),
        CalculatorChar("%", CalculatorButtonType.PERCENT),
        CalculatorChar("÷", CalculatorButtonType.OPERATOR),

        // Fila 2
        CalculatorChar("7", CalculatorButtonType.NUMBER),
        CalculatorChar("8", CalculatorButtonType.NUMBER),
        CalculatorChar("9", CalculatorButtonType.NUMBER),
        CalculatorChar("×", CalculatorButtonType.OPERATOR),

        // Fila 3
        CalculatorChar("4", CalculatorButtonType.NUMBER),
        CalculatorChar("5", CalculatorButtonType.NUMBER),
        CalculatorChar("6", CalculatorButtonType.NUMBER),
        CalculatorChar("-", CalculatorButtonType.OPERATOR),

        // Fila 4
        CalculatorChar("1", CalculatorButtonType.NUMBER),
        CalculatorChar("2", CalculatorButtonType.NUMBER),
        CalculatorChar("3", CalculatorButtonType.NUMBER),
        CalculatorChar("+", CalculatorButtonType.OPERATOR),

        // Fila 5
        CalculatorChar("0", CalculatorButtonType.NUMBER),
        CalculatorChar(",", CalculatorButtonType.NUMBER),
        CalculatorChar("⌫", CalculatorButtonType.DELETE),
        CalculatorChar("=", CalculatorButtonType.EQUALS)
    )
}

