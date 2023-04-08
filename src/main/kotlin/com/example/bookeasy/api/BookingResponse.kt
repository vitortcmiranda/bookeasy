package com.example.bookeasy.api

import java.time.Instant
import java.util.UUID

data class BookingResponse(
    val bookingId: UUID,
    //val transfer: UUID?,
    //val accommodation: UUID,
    val contactInfo: ContactInfo,
)

data class Transport(
    val transportId: UUID,
    val transportType: TransportType,
    val origin: String,
    val destination: String,
    val seat: String,
    val price: Long,
)

data class Accommodation(
    val accommodationId: UUID,
    val location: String,
    val description: String?,
    val checkIn: Instant,
    val checkOut: Instant,
    val price: Instant,
)
