package com.example.bookeasy.business.transfer

import com.example.bookeasy.api.TransferRequest
import com.example.bookeasy.business.booking.toDomain
import com.example.bookeasy.business.transfer.model.Transfer
import com.example.bookeasy.business.transfer.repository.TransferRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class TransferServiceImpl(
    private val transferRepository: TransferRepository,
) : TransferService {
    override fun findById(id: UUID): Mono<Transfer> = transferRepository.findById(id)

    override fun save(transfer: Transfer): Mono<Transfer> =
        transferRepository.save(transfer)
}
