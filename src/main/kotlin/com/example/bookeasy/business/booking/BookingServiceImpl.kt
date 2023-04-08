package com.example.bookeasy.business.booking

import com.example.bookeasy.api.BookingRequest
import com.example.bookeasy.business.booking.model.Booking
import com.example.bookeasy.business.booking.repository.BookingRepository
import com.example.bookeasy.business.transfer.TransferService
import mu.KotlinLogging
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class BookingServiceImpl(
        private val bookingRepository: BookingRepository,
        private val transferService: TransferService
) : BookingService {

    companion object {
        private val log = KotlinLogging.logger {}
    }

    override fun save(booking: BookingRequest): Mono<Booking> =
            bookingRepository.save(booking.toDomain())
                    .flatMap { savedBooking ->
                        transferService.save(booking.transfer!!.toDomain())
                                .thenReturn(savedBooking)
                    }
                    .doOnError { error ->
                        log.error("An error occurred while saving the booking", error)
                    }

    override fun findByIdCrudRepository(id: UUID): Mono<Booking> = bookingRepository.findById(id)
}


