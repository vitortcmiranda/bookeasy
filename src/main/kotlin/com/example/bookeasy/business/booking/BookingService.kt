package com.example.bookeasy.business.booking

import com.example.bookeasy.api.BookingRequest
import com.example.bookeasy.business.booking.model.BookingTable
import reactor.core.publisher.Mono
import java.util.UUID

interface BookingService {
    fun findByIdCrudRepository(id: UUID): Mono<BookingTable>

    fun saveWithCrudRepository(booking: BookingRequest): Mono<BookingTable>
}
