package com.example.bookeasy.api

import java.time.Instant

data class AccommodationRequest(
        val description: String,
        val location: String,
        val price: Long,
        val createdAt: Instant,
        val updated: Instant
)
