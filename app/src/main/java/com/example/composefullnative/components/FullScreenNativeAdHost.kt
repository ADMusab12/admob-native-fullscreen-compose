package com.example.composefullnative.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.composefullnative.R
import com.example.composefullnative.data.NativeAdState
import com.example.composefullnative.helper.loadNativeAd

@Composable
fun FullScreenNativeAdHost(
    show: Boolean,
    adUnitId: String = "",
    onDismiss: () -> Unit,
    onCtaClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    var adState by remember { mutableStateOf(NativeAdState()) }

    LaunchedEffect(show) {
        if (show) {
            adState = NativeAdState(isLoading = true)
            loadNativeAd(
                context = context,
                adUnitId = adUnitId,
                onAdLoaded = { ad -> adState = NativeAdState(isLoading = false, nativeAd = ad) },
                onError = { err -> adState = NativeAdState(isLoading = false, error = err) }
            )
        }
    }

    DisposableEffect(Unit) {
        onDispose { adState.nativeAd?.destroy() }
    }

    if (show) {
        FullScreenNativeAdDialog(
            adState = adState,
            onDismiss = onDismiss,
            onCtaClick = onCtaClick
        )
    }
}