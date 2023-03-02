package com.example.bookeasy.business.transfer

import com.example.bookeasy.api.TransferRequest
import com.example.bookeasy.business.transfer.model.TransferTable
import reactor.core.publisher.Mono
import java.util.*

interface TransferService {
    fun findByIdCrudRepository(id: UUID): Mono<TransferTable>

    fun saveWithCrudRepository(transfer: TransferRequest): Mono<TransferTable>
}
