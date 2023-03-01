package com.example.bookeasy.business.booking

import com.example.bookeasy.api.BookingRequest
import com.example.bookeasy.business.booking.model.BookingTable
import com.example.bookeasy.business.booking.repository.BookingRepositoryReactive
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.time.Instant
import java.util.*

@Service
class BookingServiceImpl(
    private val bookingReactiveRepository: BookingRepositoryReactive,
) : BookingService {

    override fun saveWithCrudRepository(booking: BookingRequest): Mono<BookingTable> =
        bookingReactiveRepository.save(booking.toDomain())

    override fun findByIdCrudRepository(id: UUID): Mono<BookingTable> = bookingReactiveRepository.findById(id)
}

private fun BookingRequest.toDomain(now: Instant = Instant.now(), random: UUID = UUID.randomUUID()): BookingTable =
    BookingTable(
//        id = random,
        hotelId = random,
        transport = this.transportType.toString(),
        flightId = random,
        roomType = this.roomType.toString(),
        checkIn = now,
        checkOut = now,
        firstName = this.contactInfo.firstName,
        lastName = this.contactInfo.lastName,
        email = this.contactInfo.email,
        phoneNumber = this.contactInfo.phoneNumber,
        createdAt = now,
        updatedAt = now,

    )
