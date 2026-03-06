package com.example.composefullnative.data

import com.google.android.gms.ads.nativead.NativeAd

data class NativeAdState(
    val isLoading: Boolean = true,
    val nativeAd: NativeAd? = null,
    val error: String? = null
)