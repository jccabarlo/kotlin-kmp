package com.example.kmp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _greetingList = MutableStateFlow<List<String>>(listOf())
    val greetingList: StateFlow<List<String>>get() = _greetingList
    private val _productList = MutableStateFlow<List<Product>>(listOf())
    val productList: StateFlow<List<Product>>get() = _productList

    init {
        viewModelScope.launch {
            Greeting().greet().collect { phrase ->
                _greetingList.update { list -> list + phrase }
            }

            Greeting().getProducts().collect { products ->
                _productList.update {  products }
            }

        }
    }
}