package com.example.kmp

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
)
