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

    override fun save(booking: BookingRequest): Mono<BookingResponse> = //TODO separate concerns
        bookingRepository.save(booking.toDomain()).doOnSuccess {
            log.info {
                "Successfully created booking, " +
                        "saving accommodation now for ${booking.contactInfo.phoneNumber}"
            }
        }.flatMap { createdBooking ->
            accommodationService.save(booking.accommodation.toDomain(createdBooking.id!!)).doOnSuccess {
                log.info {
                    "Successfully saved accommodation, saving transfer now for ${booking.contactInfo.phoneNumber}"
                }
            }
                .flatMap { accommodationSaved ->
                    transferService.save(
                        booking.transfer!!.toDomain
                            (createdBooking.id)
                    ).doOnSuccess {
                        log.info {
                            "Successfully saved transfer for ${booking.contactInfo.phoneNumber}"
                        }
                    }.map {
                        BookingResponse(
                            bookingId = createdBooking.id, transfer = it
                                .toTransferResponse(), contactInfo = ContactInfo(
                                firstName = createdBooking
                                    .firstName, lastName = createdBooking.lastName, email = createdBooking
                                    .email, phoneNumber = createdBooking.phoneNumber
                            ),
                            accommodation = accommodationSaved.toAccommodationResponse()
                        )
                    }
                }
        }

    override fun findByIdCrudRepository(id: UUID): Mono<Booking> = bookingRepository.findById(id)
}



