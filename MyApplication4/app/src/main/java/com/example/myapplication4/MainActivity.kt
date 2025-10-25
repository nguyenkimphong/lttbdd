package com.example.myapplication4  

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication4.ui.theme.MyApplication4Theme
import com.example.myapplication4.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplication4Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("list") { ComponentListScreen(navController) }
        composable("detail/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            ComponentDetailScreen(name, navController)
        }
    }
}

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.anh1),
                contentDescription = "Jetpack Compose Logo",
                modifier = Modifier.size(120.dp)
            )

            Text(
                text = "Jetpack Compose",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))
// route = list
            Button(
                onClick = { navController.navigate("list") } ,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
            ) {
                Text("I’m ready")
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentListScreen(navController: NavController) {
    val items = listOf(
        "Text", "Image", "TextField", "PasswordField", "Column", "Row"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "UI Components List",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
// route = detail
        items.forEach { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .background(Color(0xFFB3E5FC), RoundedCornerShape(8.dp))
                    .clickable { navController.navigate("detail/$item") }
                    .padding(16.dp)
            ) {
                Text(item, fontSize = 18.sp)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)

fun ComponentDetailScreen(name: String, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = when (name) {
                        "Text" -> "Text Detail"
                        "Image" -> "Images "
                        "TextField" -> "TextField"
                        "PasswordField" -> "Password Field Detail"
                        "Row" -> "Row layout"
                        else -> "Unknown Component"
                    }
                ) },
                navigationIcon = {   // Nút quay lại ở góc trái
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Quay lại"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Nội dung chi tiết từng component
            when (name) {
                "Text" -> TextExample()
                "Image" -> ImageExample()
                "TextField" -> TextFieldExample()
                "PasswordField" -> PasswordExample()
                "Column" -> ColumnExample()
                "Row" -> RowExample()
                else -> Text("Unknown Component")
            }

            Spacer(modifier = Modifier.height(32.dp))

            }
        }
    }


// === CÁC DEMO COMPONENT CƠ BẢN ===

@Composable fun TextExample() {
    Text("The quick Brown fox jumps over the lazy dog", fontSize = 18.sp)
}

@Composable fun ImageExample() {
    Image(painter = painterResource(R.drawable.anh1), contentDescription = "Logo", modifier = Modifier.size(100.dp))
}

@Composable fun TextFieldExample() {
    val text = androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf("") }
    TextField(value = text.value, onValueChange = { text.value = it }, label = { Text("Enter text") })
}

@Composable fun PasswordExample() {
    val text = androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf("") }
    TextField(value = text.value, onValueChange = { text.value = it }, label = { Text("Password") })
}

@Composable fun ColumnExample() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Item 1")
        Text("Item 2")
        Text("Item 3")
        Text("Item 4")
        Text("Item 5")
    }
}

@Composable fun RowExample() {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("A")
        Text("B")
        Text("C")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    MyApplication4Theme {
        WelcomeScreen(rememberNavController())
    }
}
