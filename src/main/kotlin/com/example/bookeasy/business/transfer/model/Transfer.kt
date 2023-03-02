package com.example.bookeasy.business.transfer.model

data class Transfer(

    val origin: String,
    val destination: String,
    val transportTicket: TransportTicket,
)

open class TransportTicket(
    private val seat: String,
    private val price: Long,
    private val type: TransportType,
)

enum class TransportType {
    BUS, AIR_PLANE
}

class AirplaneTransportTicket(val seat: String, price: Long) : TransportTicket(seat, price, TransportType.BUS)
class BusTransportTicket(val seat: String, price: Long) : TransportTicket(seat, price, TransportType.AIR_PLANE)
