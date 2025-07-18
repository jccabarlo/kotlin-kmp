package com.example.kmp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(mainViewModel: MainViewModel = viewModel()) {
    MaterialTheme {
        val greetings by mainViewModel.greetingList.collectAsStateWithLifecycle()
        val products by mainViewModel.productList.collectAsStateWithLifecycle()
        Platform().logSystemInfo()
        Column(
            modifier = Modifier
                .padding(all = 10.dp)
                .safeContentPadding()
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            greetings.forEach { greeting ->
                Text(greeting)
                HorizontalDivider()
            }

            LazyColumn {
                items(products) { it ->
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 20.dp),
                    ) {
                        Text(text = it.name)
                    }
                    HorizontalDivider()
                }
            }
        }
    }
}