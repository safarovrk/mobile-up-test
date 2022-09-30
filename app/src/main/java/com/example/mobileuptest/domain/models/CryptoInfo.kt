package com.example.mobileuptest.domain.models

data class CryptoInfo(
    val image: Image,
    val description: Description,
    val categories: List<String>
) {
    data class Description(
        val en: String
    )
    data class Image(
        val large: String
    )
}
