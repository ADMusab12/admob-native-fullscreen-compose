package com.example.composefullnative.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdErrorState(isDark: Boolean, error: String, onDismiss: () -> Unit) {
    val textColor = if (isDark) Color(0xFFF0F0FF) else Color(0xFF1A1A2E)
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(32.dp)
        ) {
            Text("😕", fontSize = 56.sp)
            Text("Ad Unavailable", color = textColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(error, color = textColor.copy(0.5f), fontSize = 13.sp, textAlign = TextAlign.Center)
            CtaButton("Close", false, isDark, onDismiss)
        }
    }
}