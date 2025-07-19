package com.example.kmp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(mainViewModel: MainViewModel = viewModel()) {
    MaterialTheme {
        Scaffold(topBar = { ToolBar() }) { paddingValues ->
            Content(
                mainViewModel, paddingValues
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ToolBar() {
    TopAppBar(
        title = { Text(text = "Home") })
}

@Composable
private fun Content(viewModel: MainViewModel, paddingValues: PaddingValues) {
    val greetings by viewModel.greetingList.collectAsStateWithLifecycle()
    val products by viewModel.productList.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ) {
        greetings.forEach { greeting ->
            Column(modifier = Modifier.height(80.dp)) {
                Text(greeting)
                HorizontalDivider()
            }
        }

//        DeviceInfo()

        LazyColumn {
            items(products) { it ->
                Column(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
                ) {
                    Text(text = it.name)
                }
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun DeviceInfo() {
    val items = makeItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(items) { row ->
            RowView(title = row.first, subtitle = row.second)
        }
    }
}

private fun makeItems(): List<Pair<String, String>> {
    val platform = Platform()
    platform.logSystemInfo()

    return listOf(
        Pair("Operating System", "${platform.osName} ${platform.osVersion}"),
        Pair("Device", platform.deviceModel),
        Pair("Density", platform.deviceDensity.toString())
    )
}

@Composable
private fun RowView(
    title: String,
    subtitle: String,
) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
}