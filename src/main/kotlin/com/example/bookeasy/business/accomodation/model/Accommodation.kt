package com.example.bookeasy.business.accomodation.model

import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

data class Accommodation(
        val bookingId: UUID,
        val id: UUID,
        val description: String,
        val location: String,
        val price: BigDecimal,
        val createdAt: Instant,
        val updatedAt: Instant
)
