package com.example.bookeasy.business.accomodation

import com.example.bookeasy.business.accomodation.model.Accommodation
import com.example.bookeasy.business.accomodation.repository.AccommodationRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class AccommodationServiceImpl(private val accommodationRepository: AccommodationRepository) : AccommodationService {
    override fun findById(id: UUID): Mono<Accommodation> = accommodationRepository.findById(id)

    companion object {
        private val log = KotlinLogging.logger {}
    }

    override fun save(accommodation: Accommodation): Mono<Accommodation> =
        accommodationRepository.save(accommodation).doOnSuccess {
            log.info {
                "Successfully saved accommodation for bookingId: [${accommodation.bookingId}]"
            }
        }.onErrorMap { ex ->
            log.error { "An error ocurred while saving accommodation for booking id: [${accommodation.bookingId}]" }
            ex
        }
}