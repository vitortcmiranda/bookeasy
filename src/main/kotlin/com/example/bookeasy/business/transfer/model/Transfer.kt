package com.example.bookeasy.business.transfer.model

import java.math.BigDecimal
import java.util.UUID

data class Transfer(
        val bookingId: UUID,
        val id: UUID,
        val origin: String,
        val destination: String,
        val transportTicket: TransportTicket,
)

open class TransportTicket(
        internal val seat: String,
        internal val price: BigDecimal,
        internal val type: TransportType,
)

enum class TransportType {
    BUS, AIR_PLANE
}

class AirplaneTransportTicket(seat: String, price: BigDecimal) : TransportTicket(seat, price, TransportType.AIR_PLANE)
class BusTransportTicket(seat: String, price: BigDecimal) : TransportTicket(seat, price, TransportType.BUS)
