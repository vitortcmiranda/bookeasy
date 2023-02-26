package com.example.bookeasy.business.booking

import com.example.bookeasy.api.BookingResponse
import com.example.bookeasy.api.ContactInfo
import com.example.bookeasy.business.booking.model.BookingTable

fun BookingTable.toBookingSessionResponse() = BookingResponse(
    flightId = this.flightId.toString(),
    hotelName = "Teste de Hotel",
    roomType = this.roomType.toString(),
    checkIn = this.checkIn,
    checkOut = this.checkOut,
    transportType = this.transport,
    contactInfo = ContactInfo(
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        phoneNumber = this.phoneNumber,
    ),

)
