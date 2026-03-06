package com.example.composefullnative.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StarRatingBar(rating: Double, isDark: Boolean) {
    val starColor = Color(0xFFFFC107)
    val emptyColor = if (isDark) Color(0xFF3A3A5C) else Color(0xFFDDDDEE)
    Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
        repeat(5) { index ->
            val filled = rating >= (index + 1)
            val half = !filled && rating >= (index + 0.5)
            Text(
                text = if (filled || half) "★" else "☆",
                color = if (filled || half) starColor else emptyColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(Modifier.width(4.dp))
        Text(
            text = String.format("%.1f", rating),
            color = if (isDark) Color(0xFFAAAAAA) else Color(0xFF666680),
            fontSize = 13.sp
        )
    }
}