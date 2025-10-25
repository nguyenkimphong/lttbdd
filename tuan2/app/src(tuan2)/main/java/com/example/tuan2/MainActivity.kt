package com.example.tuan2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import com.example.tuan2.ui.theme.Tuan2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tuan2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AgeCheckerApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeCheckerApp() {
    var name by remember { mutableStateOf("") }
    var ageInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "THỰC HÀNH 01",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Ô nhập họ tên và tuổi
        Column(
            modifier = Modifier
                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Họ và tên") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )

            OutlinedTextField(
                value = ageInput,
                onValueChange = { ageInput = it },
                label = { Text("Tuổi") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Nút kiểm tra
        Button(
            onClick = {
                val age = ageInput.toIntOrNull()
                result = when {
                    name.isBlank() || ageInput.isBlank() -> "Vui lòng nhập đầy đủ thông tin"
                    age == null -> "Tuổi không hợp lệ"
                    age < 2 -> "Em bé"
                    age in 2..5 -> "Trẻ em"
                    age in 6..65 -> "Người lớn"
                    else -> "Người già"
                }


            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5)),
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
        ) {
            Text("Kiểm tra", color = Color.White, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (result.isNotEmpty()) {
            Text(
                text = result,
                color = if (result.contains("hợp lệ")) Color.Green else Color.Red,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
