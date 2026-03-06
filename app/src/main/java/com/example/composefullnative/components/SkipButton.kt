package com.example.composefullnative.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SkipButton(isDark: Boolean, onDismiss: () -> Unit) {
    val bgColor = if (isDark) Color(0xFF1E1E2E) else Color(0xFFEEEEFF)
    val textColor = if (isDark) Color.White else Color(0xFF1A1A2E)
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (pressed) 0.92f else 1f, label = "scale")

    Surface(
        shape = RoundedCornerShape(24.dp),
        color = bgColor,
        border = BorderStroke(1.dp, Color(0xFF6C63FF).copy(alpha = 0.4f)),
        modifier = Modifier
            .scale(scale)
            .clickable { onDismiss() }
    ) {
        Row(
            Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text("✕", color = Color(0xFF6C63FF), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text("Skip", color = textColor, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}