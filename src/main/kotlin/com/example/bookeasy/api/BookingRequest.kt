package com.example.bookeasy.api

import java.math.BigDecimal

data class BookingRequest(
        val transfer: TransferRequest?,
        val accommodation: AccommodationRequest,
        val contactInfo: ContactInfo,
        val amount: BigDecimal,
)

data class ContactInfo(
        val firstName: String,
        val lastName: String,
        val email: String,
        val phoneNumber: String,
)
