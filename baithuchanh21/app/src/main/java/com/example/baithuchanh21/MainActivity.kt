package com.example.baithuchanh21

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baithuchanh21.ui.theme.Baithuchanh21Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Baithuchanh21Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EmailApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailApp() {
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var messageColor by remember { mutableStateOf(Color.Red) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Thực hành 02",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                message = "" // xóa thông báo cũ khi nhập lại
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = messageColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            onClick = {
                when {
                    email.isBlank() -> {
                        message = "Email không hợp lệ"
                        messageColor = Color.Red
                    }
                    !email.contains("@") -> {
                        message = "Email không đúng định dạng"
                        messageColor = Color.Red
                    }
                    else -> {
                        message = "Bạn đã nhập email hợp lệ"
                        messageColor = Color(0xFF008000) // màu xanh
                    }
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            modifier = Modifier
                .padding(top = 16.dp)
                .width(200.dp)
        ) {
            Text(text = "Kiểm tra", color = Color.White)
        }
    }
}
