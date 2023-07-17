package com.utsman.fakestore.home

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform