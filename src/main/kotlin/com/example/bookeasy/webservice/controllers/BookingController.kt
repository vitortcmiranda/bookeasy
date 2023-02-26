package com.example.bookeasy.webservice.controllers

import com.example.bookeasy.api.*
import com.example.bookeasy.business.booking.BookingService
import com.example.bookeasy.business.booking.toBookingSessionResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.time.Instant
import java.util.*

@RestController
@RequestMapping("/api/booking")
class BookingController(
    private val bookingService: BookingService,
) {
    @PostMapping("")
    fun saveBooking():
        Mono<BookingResponse> =
        bookingService.saveWithCrudRepository(testeRequesT()).flatMap { it.toBookingSessionResponse().toMono() }

    @GetMapping("")
    fun teste(): Mono<BookingResponse> =
        bookingService.findByIdCrudRepository(UUID.fromString("38c452a9-4dba-427e-954f-4aa15f1f6fe4"))
            .flatMap { it.toBookingSessionResponse().toMono() }
//    @GetMapping("")
//    fun getAllBookings(): Flux<BookingResponse> = bookingService.findAll().map { it.toBookingSessionResponse() }
}

private fun testeRequesT(randomUUID: UUID = UUID.randomUUID()) = BookingRequest(
    hotelId = randomUUID.toString(),
    flightId = randomUUID.toString(),
    checkIn = Instant.now(),
    checkOut = Instant.now(),
    roomType = RoomType.FAMILY,
    transportType = TransportType.FLIGHT,
    contactInfo = ContactInfo(
        firstName = randomUUID.toString(),
        lastName = randomUUID.toString(),
        email = randomUUID.toString(),
        phoneNumber = randomUUID.toString(),
    ),
)
