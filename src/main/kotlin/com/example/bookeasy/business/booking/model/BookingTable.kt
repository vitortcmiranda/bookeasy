package com.example.bookeasy.business.booking.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.*

@Table("booking")
data class BookingTable(
    @Id
    val id: UUID? = null,
    val accommodationId: UUID,
    val transferId: UUID? = null,
    val amount: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)
