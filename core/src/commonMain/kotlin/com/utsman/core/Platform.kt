package com.utsman.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform