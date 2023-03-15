package com.example.bookeasy.business.booking.repository

import com.example.bookeasy.business.booking.model.Booking
import reactor.core.publisher.Mono
import java.util.*

interface BookingRepository {
    fun findById(id: UUID): Mono<Booking>
}
