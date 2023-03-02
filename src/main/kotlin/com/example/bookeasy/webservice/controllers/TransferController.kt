package com.example.bookeasy.webservice.controllers

import com.example.bookeasy.api.*
import com.example.bookeasy.business.booking.toTransferResponse
import com.example.bookeasy.business.transfer.TransferService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*

@RestController
@RequestMapping("/api/booking/transfer")
class TransferController(
    private val transferService: TransferService,
) {
    @PostMapping("")
    fun saveBooking(@RequestBody transferRequest: TransferRequest):
            Mono<TransferResponse> =
        transferService.saveWithCrudRepository(transferRequest)
            .flatMap { it.toTransferResponse().toMono() }

    @GetMapping("/id/{id}")
    fun getById(@PathVariable id: UUID): Mono<TransferResponse> =
        transferService.findByIdCrudRepository(id)
            .flatMap { it.toTransferResponse().toMono() }
}
