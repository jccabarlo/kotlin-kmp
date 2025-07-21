package com.example.kmp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

actual open class BaseViewModel actual constructor(): ViewModel() {
    actual val scope = viewModelScope
}