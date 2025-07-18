package com.example.kmp

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

// It's highly recommended to not hardcode these keys directly in production.
// Consider using build configurations or expect/actual for platform-specific retrieval.
private const val SUPABASE_URL = ""
private const val SUPABASE_KEY = ""

val supabase = createSupabaseClient(
    supabaseUrl = SUPABASE_URL,
    supabaseKey = SUPABASE_KEY
) {
    install(Postgrest)
}
