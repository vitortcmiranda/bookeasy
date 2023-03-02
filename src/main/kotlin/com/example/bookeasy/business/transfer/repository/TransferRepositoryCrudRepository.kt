package com.example.bookeasy.business.transfer.repository

import com.example.bookeasy.business.transfer.model.TransferTable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface TransferRepositoryCrudRepository : ReactiveCrudRepository<TransferTable, UUID>
