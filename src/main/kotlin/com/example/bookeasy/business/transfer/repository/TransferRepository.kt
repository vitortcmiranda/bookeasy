package com.example.bookeasy.business.transfer.repository

import com.example.bookeasy.business.transfer.model.Transfer
import reactor.core.publisher.Mono
import java.util.*

interface TransferRepository {
    fun findById(id: UUID): Mono<Transfer>

    fun save(transfer: Transfer): Mono<Transfer>
}
