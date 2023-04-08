package com.example.bookeasy.business.accomodation.repository

import com.example.bookeasy.business.accomodation.model.Accommodation
import reactor.core.publisher.Mono
import java.util.UUID

interface AccommodationRepository {
    fun findById(accommodationId: UUID): Mono<Accommodation>
    fun save(accommodation: Accommodation): Mono<Accommodation>
}