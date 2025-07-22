package com.example.kmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

//        val articlesViewModel: ArticlesViewModel by viewModels()
        setContent {
           App()
        }
    }
}

//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//}