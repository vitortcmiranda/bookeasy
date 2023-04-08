package com.example.bookeasy.business.booking

import com.example.bookeasy.api.AccommodationRequest
import com.example.bookeasy.api.BookingRequest
import com.example.bookeasy.api.BookingResponse
import com.example.bookeasy.api.ContactInfo
import com.example.bookeasy.business.accomodation.AccommodationService
import com.example.bookeasy.business.accomodation.model.Accommodation
import com.example.bookeasy.business.booking.model.Booking
import com.example.bookeasy.business.booking.repository.BookingRepository
import com.example.bookeasy.business.transfer.TransferService
import mu.KotlinLogging
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Instant
import java.util.*

@Service
class BookingServiceImpl(
        private val bookingRepository: BookingRepository,
        private val transferService: TransferService,
        private val accommodationService: AccommodationService
) : BookingService {

    companion object {
        private val log = KotlinLogging.logger {}
    }

    override fun save(booking: BookingRequest): Mono<BookingResponse> =
            bookingRepository.save(booking.toDomain()).flatMap { createdBooking ->
                accommodationService.save(booking.accommodation.toDomain(createdBooking.id!!))
                        .flatMap { accommodationSaved ->
                            transferService.save(booking.transfer!!.toDomain
                            (createdBooking.id)).map {
                                BookingResponse(bookingId = createdBooking.id, transfer = it
                                        .toTransferResponse(), contactInfo = ContactInfo(firstName = createdBooking
                                        .firstName, lastName = createdBooking.lastName, email = createdBooking
                                        .email, phoneNumber = createdBooking.phoneNumber),
                                        accommodation = accommodationSaved.toAccommodationResponse()
                                )
                            }
                        }
            }

    override fun findByIdCrudRepository(id: UUID): Mono<Booking> = bookingRepository.findById(id)
}

private fun AccommodationRequest.toDomain(bookingId: UUID) = Accommodation(bookingId = bookingId,
        id = UUID.randomUUID(), description = this.description, location = this.location, price = this.price, createdAt
= Instant.now(), updatedAt = Instant.now())


