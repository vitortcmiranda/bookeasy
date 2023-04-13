package com.example.bookeasy.api

import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

data class BookingResponse(
    val bookingId: UUID,
    val transfer: TransferResponse,
    val accommodation: AccommodationResponse? = null,
    val contactInfo: ContactInfo? = null,
)

data class AccommodationResponse(
    val id: UUID,
    val description: String,
    val location: String,
    val price: BigDecimal,
    val createdAt: Instant
)

data class TransferResponse(
    val id: UUID,
    val bookingId: UUID,
    val origin: String,
    val destination: String,
    val seat: String,
    val transportType: String,
    val price: BigDecimal,
)
