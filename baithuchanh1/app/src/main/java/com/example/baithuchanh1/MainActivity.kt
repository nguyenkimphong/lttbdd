package com.example.baithuchanh1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.baithuchanh1.ui.navigation.AppNavGraph
import com.example.baithuchanh1.ui.theme.Baithuchanh1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Baithuchanh1Theme {
                AppNavGraph()
            }
        }
    }
}