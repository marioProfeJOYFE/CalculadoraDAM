package com.mrh.calculadoradam.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mrh.calculadoradam.data.CalculatorButtonType
import com.mrh.calculadoradam.ui.component.NumberPad
import com.mrh.calculadoradam.ui.theme.RobotoFontFamily

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var operador1 by remember { mutableStateOf("") }
    var operacion by remember { mutableStateOf("") }
    var operador2 by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$operador1 $operacion $operador2",
            fontFamily = RobotoFontFamily,
            fontSize = 42.sp,
            textAlign = TextAlign.End,
            lineHeight = 40.sp,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
        )
        NumberPad(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            onClick = { numero ->
                if(numero.type == CalculatorButtonType.NUMBER){
                    if(operacion.isEmpty()){
                        operador1 += numero.char
                    }else{
                        operador2 += numero.char
                    }
                }else if(numero.type == CalculatorButtonType.DELETE){
                    if(numero.char.equals("AC")){
                        operador1 = ""
                        operador2 = ""
                        operacion = ""
                    }
                }else{
                    operacion = numero.char
                }

            })
    }
}