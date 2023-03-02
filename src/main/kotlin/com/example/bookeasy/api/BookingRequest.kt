package com.example.bookeasy.api

import java.time.Instant

data class BookingRequest(
    val hotelId: String,
    val flightId: String?,
    val transfer: TransferRequest?,
    val checkIn: Instant,
    val checkOut: Instant,
    val contactInfo: ContactInfo,
    val amount: Long,
)

data class ContactInfo(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
)
