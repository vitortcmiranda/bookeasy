package com.example.bookeasy.business.booking

import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

@Repository
class BookingSqlRepository(private val databaseClient: DatabaseClient) {

    companion object {
        const val INSERT_SQL = """
            INSERT INTO booking (id, hotel_id, flight_id, room_type, check_in, check_out, transport_option, first_name, last_name, email, phone_number, created_at, updated_at)
            VALUES (:id, :hotel_id, :flight_id, :room_type, :check_in, :check_out, :transport_option, :first_name, :last_name, :email, :phone_number, :created_at, :updated_at)
        """
        const val FIND_BY_ID = """
            SELECT * FROM booking where id = :id
        """
    }
}

//    override fun getById(id: UUID): Mono<Booking> = databaseClient.sql(FIND_BY_ID)
//        .bind("id", id).map { row -> rowToDomain(row) }.one()
//
//    override fun save(booking: Booking): Mono<Booking> = databaseClient.sql(INSERT_SQL)
//        .bind("id", booking.id)
//        .bind("hotel_id", booking.hotelId)
//        .bind("flight_id", booking.flightId)
//        .bind("transport_option", booking.transportOption)
//        .bindEnum("room_type", booking.roomType)
//        .bind("check_in", booking.checkIn)
//        .bind("check_out", booking.checkOut)
//        .bind("first_name", booking.firstName)
//        .bind("last_name", booking.lastName)
//        .bind("email", booking.email)
//        .bind("phone_number", booking.phoneNumber)
//        .bind("created_at", booking.createdAt)
//        .bind("updated_at", booking.updatedAt)
//        .map { row ->
//            rowToDomain(row)
//        }.one()
//
//    private fun rowToDomain(row: Row) = Booking(
//        id = row["id"] as UUID,
//        hotelId = row["hotel_id"] as UUID,
//        flightId = row["flight_id"] as UUID,
//        transportOption = row["transport_option"] as String,
//        roomType = (row["room_type"] as String).toDomain(),
//        checkIn = Instant.from(row["check_in"] as Instant),
//        checkOut = Instant.from(row["check_in"] as Instant),
//        firstName = row["first_name"] as String,
//        lastName = row["last_name"] as String,
//        email = row["email"] as String,
//        phoneNumber = row["phone_number"] as String,
//        createdAt = Instant.from(row["check_in"] as Instant),
//        updatedAt = Instant.from(row["check_in"] as Instant),
//    )
// //        .map { row -> row.toDomain() }.one()
// //        .map { row -> booking.copy(id = row.get("id", UUID.class))}
// }
//
// private fun String.toDomain(): Acomodation = when (this) {
//    "STANDARD" -> Acomodation.STANDARD
//    "DELUXE" -> Acomodation.DELUXE
//    "SUITE" -> Acomodation.SUITE
//    "EXECUTIVE" -> Acomodation.EXECUTIVE
//    "FAMILY" -> Acomodation.FAMILY
//    else -> Acomodation.EXECUTIVE
// }
//
// private fun DatabaseClient.GenericExecuteSpec.bindEnum(name: String, roomType: Acomodation) =
//    bind(name, roomType.toString())
//
// private fun Row.toDomain() = Booking(
//    id = this.getT("id"),
//    hotelId = this.getT("hotel_id"),
//    transportOption = this.getT("transport"),
//    flightId = this.getT("flight_id"),
//    roomType = this.getT("roomType"),
//    checkIn = this.getT("check_in"),
//    checkOut = this.getT("check_out"),
//    firstName = this.getT("first_name"),
//    lastName = this.getT("last_name"),
//    email = this.getT("email"),
//    phoneNumber = this.getT("phone_number"),
//    createdAt = this.getT("created_at"),
//    updatedAt = this.getT("updated_at"),
// )

// private fun <T> RowsFetchSpec<T>.toDomain(): Mono<Booking> = Booking(
//    id = this
// )
