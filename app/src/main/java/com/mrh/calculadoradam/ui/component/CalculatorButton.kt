package com.mrh.calculadoradam.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.mrh.calculadoradam.ui.theme.RobotoFontFamily

@Composable
fun CalculatorButton(
    modifier: Modifier = Modifier,
    symbol: String,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
    textColor: Color = MaterialTheme.colorScheme.onSecondary,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .aspectRatio(1f) // Mantiene la forma circular (ancho = alto)
            .clip(CircleShape) // Recorta el fondo y la animación ripple en círculo
            .background(backgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true), // Efecto de pulsación recortado al borde
                onClick = onClick
            )
    ) {
        Text(
            text = symbol,
            fontSize = 32.sp,
            color = textColor,
            fontFamily = RobotoFontFamily
        )
    }
}