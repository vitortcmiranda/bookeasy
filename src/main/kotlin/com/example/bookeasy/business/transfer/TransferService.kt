package com.example.bookeasy.business.transfer

import com.example.bookeasy.api.TransferRequest
import com.example.bookeasy.business.transfer.model.Transfer
import reactor.core.publisher.Mono
import java.util.*

interface TransferService {
    fun findById(id: UUID): Mono<Transfer>

    fun save(transfer: Transfer): Mono<Transfer>
}
