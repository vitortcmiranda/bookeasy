package com.example.bookeasy.api

import java.math.BigDecimal
import java.time.Instant

data class AccommodationRequest(
        val description: String,
        val location: String,
        val price: BigDecimal,
        val createdAt: Instant,
        val updated: Instant
)
