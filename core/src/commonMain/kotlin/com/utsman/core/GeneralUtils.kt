package com.utsman.core

val String.Companion.Empty: String get() = ""
val Int.Companion.Nol: Int get() = 0

fun Int?.orNol(): Int = this ?: 0