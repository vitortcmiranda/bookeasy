package com.example.bookeasy.business.booking.repository

import com.example.bookeasy.business.booking.model.BookingTable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.*

interface BookingRepositoryReactive : ReactiveCrudRepository<BookingTable, UUID>
