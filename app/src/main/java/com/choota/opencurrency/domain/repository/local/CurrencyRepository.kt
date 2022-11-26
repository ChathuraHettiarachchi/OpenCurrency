package com.choota.opencurrency.domain.repository.local

import com.choota.opencurrency.domain.model.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getCurrencies(): Flow<List<Currency>>
    suspend fun getCurrencyById(id: Long): Currency
    suspend fun getCurrencyByTag(code: String): Currency
    suspend fun insertCurrency(currency: Currency): Long
    suspend fun updateCurrency(currency: Currency)
    suspend fun deleteCurrency(currency: Currency)
    suspend fun countByCode(code: String): Long
}