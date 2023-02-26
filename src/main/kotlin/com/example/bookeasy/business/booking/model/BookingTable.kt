package com.example.bookeasy.business.booking.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.*

@Table("booking")
data class BookingTable(
    @Id
    val id: UUID? = null,
    val hotelId: UUID,
    @Column("transport_option")
    val transport: String,
    val flightId: UUID,
    val roomType: String,
    val checkIn: Instant,
    val checkOut: Instant,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)
