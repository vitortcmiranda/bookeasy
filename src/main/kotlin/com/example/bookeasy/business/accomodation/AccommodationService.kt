package com.example.bookeasy.business.accomodation

import com.example.bookeasy.business.accomodation.model.Accommodation
import reactor.core.publisher.Mono
import java.util.UUID

interface AccommodationService {
    fun findById(id: UUID): Mono<Accommodation>
    fun save(accommodation: Accommodation): Mono<Accommodation>
}