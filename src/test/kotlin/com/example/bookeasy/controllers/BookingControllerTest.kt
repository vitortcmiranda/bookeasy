package com.example.bookeasy.controllers

import com.example.bookeasy.api.*
import com.example.bookeasy.business.accomodation.model.Accommodation
import com.example.bookeasy.business.booking.BookingService
import com.example.bookeasy.business.booking.model.Booking
import com.example.bookeasy.business.booking.toBookingSessionResponse
import com.example.bookeasy.business.booking.toDomain
import com.example.bookeasy.business.transfer.model.Transfer
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@WebFluxTest
internal class BookingControllerTest(
    @Autowired
    private val context: ApplicationContext
) {
    private val webClient = WebTestClient.bindToApplicationContext(context).build()

    @MockkBean
    private lateinit var bookingService: BookingService


    @Suppress("ReactiveStreamsUnusedPublisher")
    @Test
    fun `test endpoint of creating booking`() {
        val requestBody = buildBookingRequest()

        val bookingRequest = buildBookingRequest()

        val createdBooking: Booking = bookingRequest.toDomain(Instant.now())

        val savedAccommodation: Accommodation = bookingRequest.accommodation.toDomain(createdBooking.id!!)

        val savedTransfer: Transfer = bookingRequest.transfer!!.toDomain(createdBooking.id!!)

        val bookingResponse =
            createdBooking.toBookingSessionResponse(transfer = savedTransfer, accommodation = savedAccommodation)


        every { bookingService.save(requestBody) } returns Mono.just(bookingResponse)
        webClient.post().uri("/api/booking")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange().expectStatus().isOk()
            .expectBody()
    }

    private fun buildBookingRequest() = BookingRequest(
        transfer = TransferRequest(
            bookingId = UUID.randomUUID(), origin = "origin", destination = "destination",
            seat = "12a", transportType = TransportType.BUS, price = BigDecimal(12)
        ),
        accommodation = AccommodationRequest(
            description = "accommodation description", location = "accommodation" +
                    " location", price = BigDecimal(12), createdAt = Instant.now(), updated = Instant.now()
        ), contactInfo = ContactInfo(
            firstName = "First Name", lastName = "Last Name", email = "String",
            phoneNumber = "55555555555"
        ),
        amount = BigDecimal(24)
    )

}