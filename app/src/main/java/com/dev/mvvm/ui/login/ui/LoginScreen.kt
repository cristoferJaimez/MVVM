package com.dev.mvvm.ui.login.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.dev.mvvm.R

@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun LoginScreen(){
    Box(){
        Login()
    }
}

@Composable
fun Login() {
    HeaderAnimation()
}

@Composable
fun HeaderAnimation() {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.dev))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
}
