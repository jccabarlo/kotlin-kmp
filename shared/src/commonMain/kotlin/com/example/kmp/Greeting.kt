package com.example.kmp

import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class Greeting {
    private val rocketComponent = RocketComponent()

    fun greet(): Flow<String> = flow {
        emit(if (Random.nextBoolean()) "Hi!" else "Hello!")
        delay(1.seconds)
//        emit("Guess what this is! > ${platform.osName}")
        delay(1.seconds)
        emit(daysPhrase())
        emit(rocketComponent.launchPhrase())
    }

    fun getProducts(): Flow<List<Product>> = flow {
        try {
            val products = supabase.from("products").select().decodeList<Product>()
            emit(products);
            println("Fetched products: $products")
        } catch (e: Exception) {
            println("Error fetching products: ${e.message}")
        }
    }

}