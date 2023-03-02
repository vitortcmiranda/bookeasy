package com.example.bookeasy.business.transfer

import com.example.bookeasy.api.TransferRequest
import com.example.bookeasy.business.booking.toDomain
import com.example.bookeasy.business.transfer.model.TransferTable
import com.example.bookeasy.business.transfer.repository.TransferRepositoryCrudRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class TransferServiceImpl(
    private val transferRepositoryCrudRepository: TransferRepositoryCrudRepository,
) : TransferService {
    override fun findByIdCrudRepository(id: UUID): Mono<TransferTable> = transferRepositoryCrudRepository.findById(id)

    override fun saveWithCrudRepository(transfer: TransferRequest): Mono<TransferTable> =
        transferRepositoryCrudRepository.save(transfer.toDomain())
}
