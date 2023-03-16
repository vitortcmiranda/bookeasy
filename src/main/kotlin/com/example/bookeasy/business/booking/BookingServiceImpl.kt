package com.example.bookeasy.business.booking

import com.example.bookeasy.api.BookingRequest
import com.example.bookeasy.business.booking.model.Booking
import com.example.bookeasy.business.booking.repository.BookingRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class BookingServiceImpl(
    private val bookingReactiveRepository: BookingRepository,
) : BookingService {

    override fun saveWithCrudRepository(booking: BookingRequest): Mono<Booking> =
        bookingReactiveRepository.save(booking.toDomain())

    override fun findByIdCrudRepository(id: UUID): Mono<Booking> = bookingReactiveRepository.findById(id)
}


