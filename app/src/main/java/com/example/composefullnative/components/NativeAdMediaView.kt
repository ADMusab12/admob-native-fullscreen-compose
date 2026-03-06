package com.example.composefullnative.components

import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView

@Composable
fun NativeAdMediaView(nativeAd: NativeAd) {
    AndroidView(
        factory = { context ->
            NativeAdView(context).apply {
                val mediaView = MediaView(context).apply {
                    layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                    )
                }
                addView(mediaView)
                this.mediaView = mediaView
                setNativeAd(nativeAd)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}