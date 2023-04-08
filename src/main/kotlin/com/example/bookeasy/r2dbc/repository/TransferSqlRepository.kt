package com.example.bookeasy.r2dbc.repository

import com.example.bookeasy.business.transfer.model.Transfer
import com.example.bookeasy.business.transfer.model.TransportTicket
import com.example.bookeasy.business.transfer.model.TransportType
import com.example.bookeasy.business.transfer.repository.TransferRepository
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.util.UUID

@Repository
class TransferSqlRepository(private val databaseClient: DatabaseClient) : TransferRepository {

    companion object {
        const val FIND_BY_ID = """
            SELECT * FROM transfer where id = :id
        """
        const val INSERT = """
            INSERT INTO bookeasy.transfer
            (id, origin, destination, seat, price, ticket_type, booking_id) VALUES(:id, :origin, :destination, :seat, 
            :price, :ticket_type, :booking_id)
            RETURNING *
        """
    }


    override fun findById(id: UUID): Mono<Transfer> =
            databaseClient.sql(FIND_BY_ID).bind("id", id).map { row ->
                row.toDomain()
            }.one()

    override fun save(transfer: Transfer): Mono<Transfer> = databaseClient.sql(INSERT)
            .bind("id", transfer.id)
            .bind("origin", transfer.origin)
            .bind("destination", transfer.destination)
            .bind("seat", transfer.transportTicket.seat)
            .bind("price", transfer.transportTicket.price)
            .bind("ticket_type", transfer.transportTicket.type.toString())
            .bind("booking_id", transfer.bookingId).map { row -> row.toDomain() }.one()
}

private fun Row.toDomain() = Transfer(
        id = this.get("id") as UUID,
        origin = this.get("origin") as String,
        destination = this.get("destination") as String,
        bookingId = this.get("booking_id") as UUID,
        transportTicket = TransportTicket(
                seat = this.get("seat") as String,
                price = this.get("price") as BigDecimal,
                type = getTypeEnum(this.get("ticket_type") as String)
        )

)

private fun getTypeEnum(entry: String) = when (entry) {
    "BUS" -> TransportType.BUS
    else -> TransportType.AIR_PLANE
}