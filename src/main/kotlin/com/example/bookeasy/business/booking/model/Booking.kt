package com.example.bookeasy.business.booking.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.Instant
import java.util.*

data class Booking(
    val id: UUID? = null,
    val accommodationId: UUID,
    val transferId: UUID? = null,
    val amount: BigDecimal,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)
