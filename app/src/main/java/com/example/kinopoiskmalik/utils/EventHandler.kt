package com.example.kinopoiskmalik.utils

interface EventHandler<T> {
    fun obtainEvent(event: T)
}