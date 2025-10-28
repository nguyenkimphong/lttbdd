package com.example.baithuchanh1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.baithuchanh1.ui.screen.ConfirmScreen
import com.example.baithuchanh1.ui.screen.ForgotPasswordScreen
import com.example.baithuchanh1.ui.screen.ResetPasswordScreen
import com.example.baithuchanh1.ui.screen.VerifyCodeScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "forgot") {

        composable("forgot") {
            ForgotPasswordScreen(navController)
        }

        composable(
            route = "verify/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { back ->
            val email = back.arguments?.getString("email") ?: ""
            VerifyCodeScreen(navController = navController, email = email)
        }

        composable(
            route = "reset/{email}/{code}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("code") { type = NavType.StringType }
            )
        ) { back ->
            val email = back.arguments?.getString("email") ?: ""
            val code = back.arguments?.getString("code") ?: ""
            ResetPasswordScreen(navController = navController, email = email, code = code)
        }

        composable(
            route = "confirm/{email}/{code}/{password}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("code") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )
        ) { back ->
            val email = back.arguments?.getString("email") ?: ""
            val code = back.arguments?.getString("code") ?: ""
            val password = back.arguments?.getString("password") ?: ""

            ConfirmScreen(navController = navController, email = email, code = code, password = password)
        }
    }
}
