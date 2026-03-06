package com.example.composefullnative.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DemoScreen() {
    var showAd by remember { mutableStateOf(false) }
    val isDark = isSystemInDarkTheme()
    val bg = if (isDark) Color(0xFF0D0D1A) else Color(0xFFF8F7FF)
    val textColor = if (isDark) Color.White else Color(0xFF1A1A2E)

    Box(
        Modifier
            .fillMaxSize()
            .background(bg),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Full Screen Native Ad Demo", color = textColor, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            CtaButton("Show Ad", false, isDark) { showAd = true }
        }
    }

    FullScreenNativeAdHost(
        show = showAd,
        onDismiss = { showAd = false },
        onCtaClick = {  }
    )
}