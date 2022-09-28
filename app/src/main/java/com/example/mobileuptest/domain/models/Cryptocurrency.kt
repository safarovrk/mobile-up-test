package com.example.mobileuptest.domain.models

data class Cryptocurrency(
    val image: String,
    val name: String,
    val current_price: Double,
    val price_change_percentage_24h: Double,
    val symbol: String
)
