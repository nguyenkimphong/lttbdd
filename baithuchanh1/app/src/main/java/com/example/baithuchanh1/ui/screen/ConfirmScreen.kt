package com.example.baithuchanh1.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.baithuchanh1.R
import com.example.baithuchanh1.ui.common.CommonButton
import com.example.baithuchanh1.ui.common.CircleBackButton

@Composable
fun ConfirmScreen(email: String, code: String, password: String , navController:NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    { Row(modifier = Modifier.fillMaxWidth()) {
        CircleBackButton { navController.popBackStack() }
    }
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier.height(12.dp))
        Image(painter = painterResource(id = R.drawable.uth_logo), contentDescription = "Logo", modifier = Modifier.size(100.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text("SmartTasks", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(12.dp))
        Text("Confirm", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))

        // show details
        Text("Email: $email")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Code: $code")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Password: $password")
        Spacer(modifier = Modifier.height(24.dp))

        CommonButton(text = "Submit", enabled = true) {
            // TODO: Call backend / show snackbar
        }
    }
}