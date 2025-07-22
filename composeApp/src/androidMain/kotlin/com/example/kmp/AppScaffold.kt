package com.example.kmp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kmp.articles.ArticlesViewModel
import com.example.kmp.screens.ArticlesScreen
import com.example.kmp.screens.DeviceInfoScreen
import com.example.kmp.screens.Screens

@Composable
fun AppScaffold(articlesViewModel: ArticlesViewModel) {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            articlesViewModel
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    articlesViewModel: ArticlesViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.ARTICLES.route,
        modifier = modifier,
    ) {
        composable(Screens.ARTICLES.route) {
            ArticlesScreen(
                onAboutButtonClick = { navController.navigate(Screens.DEVICE_INFO.route) },
                articlesViewModel,
            )
        }

        composable(Screens.DEVICE_INFO.route) {
            DeviceInfoScreen(
                onUpButtonClick = { navController.popBackStack() }
            )
        }
    }
}