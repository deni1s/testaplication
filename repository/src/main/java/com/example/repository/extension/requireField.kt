package com.example.repository.extension

fun <T> T?.requireField() = this ?: throw RuntimeException("$this is required")

