package com.utsman.network

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform