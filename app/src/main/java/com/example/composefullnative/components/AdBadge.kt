package com.example.composefullnative.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdBadge(isDark: Boolean) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = Color(0xFF10B981).copy(alpha = 0.15f),
        border = BorderStroke(1.dp, Color(0xFF10B981).copy(alpha = 0.5f))
    ) {
        Text(
            text = "Ad",
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            color = Color(0xFF10B981),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
    }
}