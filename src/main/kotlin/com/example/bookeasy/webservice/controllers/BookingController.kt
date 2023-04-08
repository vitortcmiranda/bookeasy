package com.example.bookeasy.webservice.controllers

import com.example.bookeasy.api.*
import com.example.bookeasy.business.booking.BookingService
import com.example.bookeasy.business.booking.toBookingSessionResponse
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*

@RestController
@RequestMapping("/api/booking")
class BookingController(
    private val bookingService: BookingService,
) {
    @PostMapping("")
    fun saveBooking(@RequestBody bookingRequest: BookingRequest):
            Mono<BookingResponse> =
        bookingService.save(bookingRequest)
            .flatMap { it.toBookingSessionResponse().toMono() }

    @GetMapping("/id/{id}")
    fun getById(@PathVariable id: UUID): Mono<BookingResponse> =
        bookingService.findByIdCrudRepository(id)
            .flatMap { it.toBookingSessionResponse().toMono() }
}
