package com.example.bookeasy.business.accomodation

import com.example.bookeasy.business.accomodation.model.Accommodation
import com.example.bookeasy.business.accomodation.repository.AccommodationRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class AccommodationServiceImpl(private val accommodationRepository: AccommodationRepository) : AccommodationService {
    override fun findById(id: UUID): Mono<Accommodation> = accommodationRepository.findById(id)

    override fun save(accommodation: Accommodation): Mono<Accommodation> = accommodationRepository.save(accommodation)
}