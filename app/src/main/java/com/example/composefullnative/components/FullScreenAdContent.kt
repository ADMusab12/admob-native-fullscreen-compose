package com.example.composefullnative.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.ads.nativead.NativeAd

@Composable
fun FullScreenAdContent(
    nativeAd: NativeAd,
    isDark: Boolean,
    colorScheme: ColorScheme,
    ctaPressed: Boolean,
    onCtaClick: () -> Unit
) {
    val cardBg = if (isDark) Color(0xFF13131F).copy(alpha = 0.92f) else Color.White.copy(alpha = 0.92f)
    val textPrimary = if (isDark) Color(0xFFF0F0FF) else Color(0xFF1A1A2E)
    val textSecondary = if (isDark) Color(0xFFAAAABB) else Color(0xFF5A5A7A)
    val dividerColor = if (isDark) Color(0xFF2A2A3F) else Color(0xFFE8E8F0)
    val accentGradient = Brush.linearGradient(listOf(Color(0xFF6C63FF), Color(0xFFFF6584)))

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 80.dp, bottom = 32.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Media Card
        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(12.dp),
            colors = CardDefaults.cardColors(containerColor = cardBg),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                NativeAdMediaView(nativeAd)

                // Gradient overlay on media
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, cardBg),
                                startY = 120f
                            )
                        )
                )
            }
        }

        // Info Card
        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = cardBg),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {

                // Advertiser row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(accentGradient),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = (nativeAd.advertiser?.firstOrNull() ?: "A").toString(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                    Column {
                        Text(
                            text = nativeAd.advertiser ?: "Sponsored",
                            color = textPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Verified Advertiser",
                            color = Color(0xFF10B981),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Divider(color = dividerColor, thickness = 0.5.dp)

                // Headline
                Text(
                    text = nativeAd.headline ?: "Discover Something Amazing",
                    color = textPrimary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 28.sp
                )

                // Body
                nativeAd.body?.let {
                    Text(
                        text = it,
                        color = textSecondary,
                        fontSize = 14.sp,
                        lineHeight = 21.sp
                    )
                }

                // Star Rating
                nativeAd.starRating?.let { rating ->
                    StarRatingBar(rating, isDark)
                }

                // Price / Store row
                if (nativeAd.price != null || nativeAd.store != null) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        nativeAd.store?.let {
                            InfoChip(label = it, icon = "🏪", isDark = isDark)
                        }
                        nativeAd.price?.let {
                            InfoChip(label = it, icon = "💰", isDark = isDark)
                        }
                    }
                }
            }
        }

        // CTA Button
        CtaButton(
            label = nativeAd.callToAction ?: "Learn More",
            pressed = ctaPressed,
            isDark = isDark,
            onClick = onCtaClick
        )

        Spacer(Modifier.height(8.dp))

        // Disclaimer
        Text(
            text = "This is a paid advertisement. Tap 'Skip' to close after the countdown.",
            color = textSecondary.copy(alpha = 0.6f),
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}