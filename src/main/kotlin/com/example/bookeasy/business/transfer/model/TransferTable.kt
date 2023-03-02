package com.example.bookeasy.business.transfer.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("transfer")
data class TransferTable(
    @Id
    val id: UUID,
    val bookingId: UUID,
    val origin: String,
    val destination: String,
    val seat: String,
    val price: Long,
    val transportType: Type,
)

enum class Type {
    BUS, AIR_PLANE
}
