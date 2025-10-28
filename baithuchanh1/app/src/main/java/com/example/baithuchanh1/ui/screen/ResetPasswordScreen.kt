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
import com.example.baithuchanh1.ui.common.CircleBackButton
import com.example.baithuchanh1.ui.common.CommonButton
import com.example.baithuchanh1.ui.common.CommonTextField
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ResetPasswordScreen(navController: NavController, email: String, code: String) {
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    val ok = password.length >= 6 && password == confirm

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(modifier = Modifier.fillMaxWidth()) {
            CircleBackButton { navController.popBackStack() }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Image(painter = painterResource(id = R.drawable.uth_logo), contentDescription = "Logo", modifier = Modifier.size(100.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text("SmartTasks", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(12.dp))
        Text("Create new password", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Your new password must be different from previously used password", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(18.dp))
        CommonTextField(value = password, onValueChange = { password = it }, label = "Password", isPassword = true)
        Spacer(modifier = Modifier.height(12.dp))
        CommonTextField(value = confirm, onValueChange = { confirm = it }, label = "Confirm Password", isPassword = true)
        Spacer(modifier = Modifier.height(18.dp))
        CommonButton(text = "Next", enabled = ok) {
            navController.navigate("confirm/${email}/${code}/${password}")
        }
    }
}