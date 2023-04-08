package com.example.bookeasy.api

import org.springframework.data.annotation.Id
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

data class BookingResponse(
        val bookingId: UUID,
        val transfer: TransferResponse,
        val accommodation: AccommodationResponse,
        val contactInfo: ContactInfo,
)

data class Transfer(
        val transportId: UUID,
        val transportType: TransportType,
        val origin: String,
        val destination: String,
        val seat: String,
        val price: Long,
)

data class AccommodationResponse(
        val id: UUID,
        val description: String,
        val location: String,
        val price: BigDecimal,
        val createdAt: Instant
)
