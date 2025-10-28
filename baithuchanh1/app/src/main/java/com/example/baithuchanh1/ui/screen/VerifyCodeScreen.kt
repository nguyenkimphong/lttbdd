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
import com.example.baithuchanh1.ui.common.OtpInput
import androidx.compose.ui.text.style.TextAlign

@Composable
fun VerifyCodeScreen(navController: NavController, email: String) {
    var code by remember { mutableStateOf("") }
    val isOk = code.length == 5

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            CircleBackButton { navController.popBackStack() }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Image(painter = painterResource(id = R.drawable.uth_logo), contentDescription = "Logo", modifier = Modifier.size(100.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text("SmartTasks", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(12.dp))
        Text("Verify Code", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Enter the code we just sent to your registered Email", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(18.dp))
        OtpInput(value = code, onValueChange = { code = it }, length = 5)
        Spacer(modifier = Modifier.height(20.dp))
        CommonButton(text = "Next", enabled = isOk) {
            navController.navigate("reset/${email}/${code}")
        }
    }
}
