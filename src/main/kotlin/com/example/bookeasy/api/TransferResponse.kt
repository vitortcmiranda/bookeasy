package com.example.bookeasy.api

import java.math.BigDecimal
import java.util.*

data class TransferResponse(
        val id: UUID,
        val bookingId: UUID,
        val origin: String,
        val destination: String,
        val seat: String,
        val transportType: String,
        val price: BigDecimal,
)
