package com.example.bookeasy.api

import java.time.Instant

data class BookingResponse(
    val hotelName: String = "Hotel de teste",
    val flightId: String?,
    val roomType: String,
    val transportType: String,
    val checkIn: Instant,
    val checkOut: Instant,
    val contactInfo: ContactInfo
)


