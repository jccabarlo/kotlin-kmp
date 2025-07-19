package com.example.kmp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
    Platform().logSystemInfo()
    MaterialTheme {
        Scaffold(topBar = { ToolBar() }) { paddingValues ->
            Content(
                mainViewModel,
                paddingValues
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ToolBar() {
    TopAppBar(
        title = { Text(text = "Home") }
    )
}

@Composable
private fun Content(viewModel: MainViewModel, paddingValues: PaddingValues) {
    val greetings by viewModel.greetingList.collectAsStateWithLifecycle()
    val products by viewModel.productList.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ) {
        greetings.forEach { greeting ->
            Column(modifier = Modifier.height(80.dp)) {
                Text(greeting)
                HorizontalDivider()
            }
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