package com.example.bookeasy.api

import java.util.*

data class TransferResponse(
    val id: UUID,
    val bookingId: UUID,
    val origin: String,
    val destination: String,
    val seat: String,
    val transportType: String,
    val price: Long = 500,
)
