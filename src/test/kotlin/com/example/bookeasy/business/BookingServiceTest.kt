package com.example.bookeasy.business

import com.example.bookeasy.api.*
import com.example.bookeasy.business.accomodation.AccommodationService
import com.example.bookeasy.business.accomodation.model.Accommodation
import com.example.bookeasy.business.booking.*
import com.example.bookeasy.business.booking.model.Booking
import com.example.bookeasy.business.booking.repository.BookingRepository
import com.example.bookeasy.business.transfer.TransferService
import com.example.bookeasy.business.transfer.model.Transfer
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.time.Instant
import java.util.*

class BookingServiceTest {

    private lateinit var bookingService: BookingService

    @MockK
    private lateinit var bookingRepository: BookingRepository

    @MockK
    private lateinit var transferService: TransferService

    @MockK
    private lateinit var accommodationService: AccommodationService


    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        bookingService = BookingServiceImpl(bookingRepository, transferService, accommodationService)
    }

    @Test
    fun `should save booking successfully`() {

        val bookingRequest = buildBookingRequest()

        val createdBooking: Booking = bookingRequest.toDomain(Instant.now())

        val savedAccommodation: Accommodation = bookingRequest.accommodation.toDomain(createdBooking.id!!)

        val savedTransfer: Transfer = bookingRequest.transfer!!.toDomain(createdBooking.id!!)

        val bookingResponse =
            createdBooking.toBookingSessionResponse(transfer = savedTransfer, accommodation = savedAccommodation)


        every { bookingRepository.save(any()) } returns Mono.just(createdBooking)
        every { accommodationService.save(any()) } returns Mono.just(savedAccommodation)
        every { transferService.save(any()) } returns Mono.just(savedTransfer)


        val result = bookingService.save(bookingRequest).block()

        assertEquals(bookingResponse, result)
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