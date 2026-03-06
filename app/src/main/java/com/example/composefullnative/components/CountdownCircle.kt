package com.example.composefullnative.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CountdownCircle(
    seconds: Int,
    total: Int,
    isDark: Boolean,
    onDismissEnabled: () -> Unit
) {
    val progress = seconds.toFloat() / total
    val strokeColor = Color(0xFF6C63FF)
    val bgColor = if (isDark) Color(0xFF1E1E2E) else Color(0xFFEEEEFF)
    val textColor = if (isDark) Color.White else Color(0xFF1A1A2E)

    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(52.dp)) {
        Canvas(Modifier.fillMaxSize()) {
            drawCircle(bgColor)
            drawArc(
                color = strokeColor,
                startAngle = -90f,
                sweepAngle = 360f * progress,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(
                    width = 3.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        Text(
            text = "$seconds",
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }

    LaunchedEffect(Unit) { onDismissEnabled() }
}