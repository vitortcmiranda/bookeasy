package com.example.bookeasy.api

import java.util.*

data class TransferRequest(
    val bookingId: UUID,
    val origin: String,
    val destination: String,
    val seat: String,
    val transportType: TransportType,
    val price: Long = 500,
)

enum class TransportType {
    BUS, AIR_PLANE
}
