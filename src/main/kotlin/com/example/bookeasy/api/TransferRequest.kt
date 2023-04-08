package com.example.bookeasy.api

import java.math.BigDecimal
import java.util.*

data class TransferRequest(
        val bookingId: UUID,
        val origin: String,
        val destination: String,
        val seat: String,
        val transportType: TransportType,
        val price: BigDecimal,
)

enum class TransportType {
    BUS, AIR_PLANE
}
