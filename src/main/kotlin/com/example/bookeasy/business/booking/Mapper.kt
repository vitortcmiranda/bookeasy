package com.example.bookeasy.business.booking

import com.example.bookeasy.api.*
import com.example.bookeasy.business.booking.model.Booking
import com.example.bookeasy.business.transfer.model.AirplaneTransportTicket
import com.example.bookeasy.business.transfer.model.BusTransportTicket
import com.example.bookeasy.business.transfer.model.Transfer
import java.time.Instant
import java.util.UUID

fun Booking.toBookingSessionResponse() = BookingResponse(
        bookingId = this.id!!,
        //accommodation = this.accommodationId,
        //transfer = this.transferId,
        contactInfo = ContactInfo(firstName, lastName, email, phoneNumber),
)

fun BookingRequest.toDomain(now: Instant = Instant.now(), random: UUID = UUID.randomUUID()): Booking =
        Booking(
                id = UUID.randomUUID(),
                //accommodationId = random,
                //transferId = random,
                firstName = this.contactInfo.firstName,
                lastName = this.contactInfo.lastName,
                email = this.contactInfo.email,
                phoneNumber = this.contactInfo.phoneNumber,
                amount = this.amount,
                createdAt = now,
                updatedAt = now,
        )

fun TransferRequest.toDomain(): Transfer = Transfer(
        id = UUID.randomUUID(),
        origin = this.origin,
        destination = this.destination,
        transportTicket = when (this.transportType) { //TODO create a factory for this
            TransportType.BUS -> BusTransportTicket(seat, price)
            TransportType.AIR_PLANE -> AirplaneTransportTicket(seat, price)
        },
        bookingId = this.bookingId
)

fun TransportType.toDomain(): com.example.bookeasy.business.transfer.model.TransportType = when (this) {
    TransportType.AIR_PLANE -> com.example.bookeasy.business.transfer.model.TransportType.AIR_PLANE
    TransportType.BUS -> com.example.bookeasy.business.transfer.model.TransportType.BUS
}

fun Transfer.toTransferResponse(): TransferResponse = TransferResponse(
        id = this.id!!,
        bookingId = this.bookingId,
        destination = this.destination,
        origin = this.origin,
        seat = this.transportTicket.seat,
        transportType = this.transportTicket.type.toString(),
        price = this.transportTicket.price,

        )
