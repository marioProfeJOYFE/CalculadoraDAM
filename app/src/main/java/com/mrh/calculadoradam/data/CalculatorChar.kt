package com.mrh.calculadoradam.data

/**
 *  Clase CalculatorButton
 *
 *  @author Mario Rios Holgado
 *
 *  @param char  Caracter del teclado de la calculadora
 *  @param type  Tipo de boton. Por defecto, NUMBER
 *
 */
data class CalculatorChar(
    val char: String,
    val type: CalculatorButtonType = CalculatorButtonType.NUMBER
)
