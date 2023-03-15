package com.example.bookeasy.business.booking

import com.example.bookeasy.api.*
import com.example.bookeasy.business.booking.model.Booking
import com.example.bookeasy.business.transfer.model.TransferTable
import java.time.Instant
import java.util.*

fun Booking.toBookingSessionResponse() = BookingResponse(
    bookingId = this.id!!,
    accommodation = this.accommodationId,
    transfer = this.transferId,
    contactInfo = ContactInfo(firstName, lastName, email, phoneNumber),
)

fun BookingRequest.toDomain(now: Instant = Instant.now(), random: UUID = UUID.randomUUID()): Booking =
    Booking(
        accommodationId = random,
        transferId = random,
        firstName = this.contactInfo.firstName,
        lastName = this.contactInfo.lastName,
        email = this.contactInfo.email,
        phoneNumber = this.contactInfo.phoneNumber,
        amount = this.amount,
        createdAt = now,
        updatedAt = now,
    )

fun TransferRequest.toDomain(): TransferTable = TransferTable(
    bookingId = this.bookingId,
    origin = this.origin,
    destination = this.destination,
    seat = this.seat,
    price = this.price,
    transportType = this.transportType.toDomain(),
)

fun TransportType.toDomain(): com.example.bookeasy.business.transfer.model.TransportType = when (this) {
    TransportType.AIR_PLANE -> com.example.bookeasy.business.transfer.model.TransportType.AIR_PLANE
    TransportType.BUS -> com.example.bookeasy.business.transfer.model.TransportType.BUS
}

fun TransferTable.toTransferResponse(): TransferResponse = TransferResponse(
    id = this.id!!,
    bookingId = this.bookingId,
    destination = this.destination,
    origin = this.origin,
    seat = this.seat,
    transportType = this.transportType.toString(),
    price = this.price,

)
