package com.example.bookeasy.business.transfer

import com.example.bookeasy.business.transfer.model.Transfer
import com.example.bookeasy.business.transfer.repository.TransferRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class TransferServiceImpl(
    private val transferRepository: TransferRepository,
) : TransferService {

    companion object {
        private val log = KotlinLogging.logger {}
    }

    override fun findById(id: UUID): Mono<Transfer> = transferRepository.findById(id)

    override fun save(transfer: Transfer): Mono<Transfer> = transferRepository.save(transfer)
        .doOnSuccess {
            log.info {
            }
            "Successfully saved transfer for booking id: [${transfer.bookingId}]"
        }.onErrorMap { ex ->
            log.error { "An error ocurred while saving transfer for booking id: [${transfer.bookingId}]" }
            ex
        }
}


