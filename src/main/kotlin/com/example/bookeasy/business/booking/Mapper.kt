package com.example.bookeasy.business.booking

import com.example.bookeasy.api.BookingResponse
import com.example.bookeasy.api.ContactInfo
import com.example.bookeasy.business.booking.model.BookingTable

fun BookingTable.toBookingSessionResponse() = BookingResponse(
    bookingId = this.id!!,
    accommodation = this.accommodationId,
    transfer = this.transferId,
    contactInfo = ContactInfo(firstName, lastName, email, phoneNumber),
)
