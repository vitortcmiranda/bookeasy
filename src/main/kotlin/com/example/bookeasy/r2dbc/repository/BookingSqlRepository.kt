package com.example.bookeasy.r2dbc.repository

import com.example.bookeasy.business.booking.model.Booking
import com.example.bookeasy.business.booking.repository.BookingRepository
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.bind
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

@Repository
class BookingSqlRepository(private val databaseClient: DatabaseClient) : BookingRepository {

    companion object {
        const val INSERT_SQL = """
            INSERT INTO booking (id, accommodation_id, transfer_id, first_name, last_name, email, phone_number, created_at, updated_at, amount)
            VALUES (:id, :accommodation_id, :transfer_id, :first_name, :last_name, :email, :phone_number, :created_at, :updated_at, :amount)
            RETURNING *
        """
        const val FIND_BY_ID = """
            SELECT * FROM booking where id = :id
        """
    }


    override fun findById(id: UUID): Mono<Booking> =
        databaseClient.sql(FIND_BY_ID).bind("id", id).map { row ->
            row.toDomain()
        }.one()


    override fun save(booking: Booking) =
        databaseClient.sql(INSERT_SQL).bind("id", booking.id).bind("accommodation_id", booking.accommodationId)
            .bind("transfer_id", booking.transferId).bind("first_name", booking.firstName)
            .bind("last_name", booking.firstName).bind("email", booking.email).bind("phone_number", booking.phoneNumber)
            .bind("created_at", booking.createdAt).bind("updated_at", booking.updatedAt).bind("amount", booking.amount)
            .map { row -> row.toDomain() }.one()
}

private fun Row.toDomain() = Booking(
    id = this.get("id") as UUID,
    accommodationId = this.get("accommodation_id") as UUID,
    transferId = this.get("transfer_id") as UUID,
    amount = this.get("amount") as BigDecimal,
    firstName = this.get("first_name") as String,
    lastName = this.get("last_name") as String,
    email = this.get("email") as String,
    phoneNumber = this.get("phone_number") as String,
    createdAt = this.get("created_at", Instant::class.java)!!,
    updatedAt = this.get("updated_at", Instant::class.java)!!
)
