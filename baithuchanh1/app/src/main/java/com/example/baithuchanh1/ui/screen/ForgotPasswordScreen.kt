package com.example.baithuchanh1.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.baithuchanh1.R
import com.example.baithuchanh1.ui.common.CommonButton
import com.example.baithuchanh1.ui.common.CommonTextField
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    val isEmailValid = email.contains("@") && email.contains(".")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Image(painter = painterResource(id = R.drawable.uth_logo), contentDescription = "Logo", modifier = Modifier.size(110.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text("SmartTasks", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(24.dp))
        Text("Forget Password?", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Enter your Email, we will send you a verification code.", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(20.dp))
        CommonTextField(
            value = email,
            onValueChange = { email = it },
            label = "Your Email",
            isPassword = false
        )
        Spacer(modifier = Modifier.height(20.dp))
        CommonButton(text = "Next",     enabled = email.isNotBlank() && isEmailValid) {
            navController.navigate("verify/${email}")
        }
    }
}