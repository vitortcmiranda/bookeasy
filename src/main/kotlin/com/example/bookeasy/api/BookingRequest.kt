package com.example.bookeasy.api

import java.time.Instant

data class BookingRequest(
    val hotelId: String,
    val flightId: String?,
    val roomType: RoomType,
    val transportType: TransportType,
    val checkIn: Instant,
    val checkOut: Instant,
    val contactInfo: ContactInfo
)

data class ContactInfo(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String
)

enum class RoomType {
    STANDARD, DELUXE, SUITE, EXECUTIVE, FAMILY
}

enum class TransportType {
    FLIGHT, BUS, RENT_CAR

}

