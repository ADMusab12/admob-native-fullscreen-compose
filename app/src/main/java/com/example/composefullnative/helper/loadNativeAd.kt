package com.example.composefullnative.helper

import android.content.Context
import com.example.composefullnative.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions

fun loadNativeAd(
    context: Context,
    adUnitId: String = "",
    onAdLoaded: (NativeAd) -> Unit,
    onError: (String) -> Unit
) {
    val adLoader = AdLoader.Builder(context, adUnitId)
        .forNativeAd { nativeAd -> onAdLoaded(nativeAd) }
        .withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(error: LoadAdError) {
                onError(error.message)
            }
        })
        .withNativeAdOptions(NativeAdOptions.Builder().build())
        .build()

    adLoader.loadAd(AdRequest.Builder().build())
}