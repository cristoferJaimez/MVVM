package com.dev.mvvm.ui.login.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.dev.mvvm.R
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            //.padding(4.dp)
    ) {
        Login(Modifier.align(Alignment.Center), viewModel)
    }
}


@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel, ) {

    val email : String by viewModel.email.observeAsState(initial = "")
    val password : String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if(isLoading){
        Box(Modifier.fillMaxSize()) {
           CircularProgressIndicator(Modifier.align(Alignment.Center))
            //LoadingAnimation(Modifier.align(Alignment.CenterHorizontally))
        }
    }else{
        Column(modifier = modifier) {
            HeaderAnimation(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .scale(1f))
            Spacer(modifier = Modifier.padding(2.dp))
            EmailField(email, { viewModel.onLoginChanged(it, password) })
            Spacer(modifier = Modifier.padding(2.dp))
            PasswordField(password, { viewModel.onLoginChanged(email, it) })
            Spacer(modifier = Modifier.padding(2.dp))
            ForgotPassword(Modifier.align(Alignment.End))
            Spacer(modifier = Modifier.padding(2.dp))
            LoginButton(loginEnable) {
                coroutineScope.launch { viewModel.onLoginSelected() }
                 }
            Spacer(modifier = Modifier.padding(2.dp))
        }
    }
    

}

@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(onClick = {onLoginSelected()},
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF58D0E),
            disabledContainerColor = Color(0xFDAAA9AA)
        ),
        enabled = loginEnable
        ) {
        Text(
            text = "Iniciar sesión",
            color = Color.White
        )
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text( text = "¿Olvidates la contraseña?",
            modifier = modifier.clickable {  },
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5F5F5F)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(password: String, onTextFieldChanged:(String) -> Unit) {
    TextField(value = password,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {onTextFieldChanged(it)},
        placeholder = { Text(text = "Password")},
        keyboardOptions = KeyboardOptions(keyboardType =  KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFFFFFFF),
            containerColor = Color(0xFFC9C6C6),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(email: String, onTextFieldChanged:(String) -> Unit) {

    TextField(value = email,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {onTextFieldChanged(it)},
        placeholder = { Text(text = "Email")},
        keyboardOptions = KeyboardOptions(keyboardType =  KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFFFFFFF),
            containerColor = Color(0xFFC9C6C6),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )

}

@Composable
fun HeaderAnimation(modifier: Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier
    )
}


@Composable
fun LoadingAnimation(align: Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.dev))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,

    )
}
