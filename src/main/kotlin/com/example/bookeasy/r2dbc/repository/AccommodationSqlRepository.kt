package com.example.bookeasy.r2dbc.repository

import com.example.bookeasy.business.accomodation.model.Accommodation
import com.example.bookeasy.business.accomodation.repository.AccommodationRepository
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Repository
class AccommodationSqlRepository(private val databaseClient: DatabaseClient) : AccommodationRepository {

    companion object {
        const val FIND_BY_ID = """
            SELECT * FROM accommodation where id = :id
        """

        const val INSERT = """
            INSERT INTO accommodation
            (booking_id, id, description, "location", price, created_at, updated_at)
            VALUES(:booking_id, :id, :description, :location, :price, :created_at, :updated_at)
            RETURNING *
        """
    }

    override fun findById(accommodationId: UUID): Mono<Accommodation> =
            databaseClient.sql(FIND_BY_ID)
                    .bind("id", accommodationId).map { row -> row.toDomain() }.one()

    override fun save(accommodation: Accommodation): Mono<Accommodation> =
            databaseClient.sql(INSERT).bind("booking_id", accommodation.bookingId)
                    .bind("id", accommodation.id)
                    .bind("description", accommodation.description).bind("location", accommodation.location)
                    .bind("price", accommodation.price).bind("created_at", accommodation.createdAt)
                    .bind("updated_at", accommodation.updatedAt).map { row -> row.toDomain() }.one()
}

private fun Row.toDomain() = Accommodation(
        bookingId = this.get("booking_id") as UUID,
        id = this.get("id") as UUID,
        description = this.get("description") as String,
        location = this.get("location") as String,
        price = this.get("price") as BigDecimal,
        updatedAt = this.get("updated_at", Instant::class.java)!!,
        createdAt = this.get("created_at", Instant::class.java)!!
)