package com.choota.opencurrency.data.repository.local

import com.choota.opencurrency.data.local.dao.CurrencyDao
import com.choota.opencurrency.domain.model.Currency
import com.choota.opencurrency.domain.repository.local.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val dao: CurrencyDao): CurrencyRepository {
    override fun getCurrencies(): Flow<List<Currency>> {
        return dao.getCurrencies()
    }

    override suspend fun getCurrencyById(id: Long): Currency {
        return dao.getCurrencyById(id)
    }

    override suspend fun getCurrencyByTag(code: String): Currency {
        return dao.getCurrencyByTag(code)
    }

    override suspend fun insertCurrency(currency: Currency): Long {
        return dao.insertCurrency(currency)
    }

    override suspend fun updateCurrency(currency: Currency) {
        return dao.updateCurrency(currency)
    }

    override suspend fun deleteCurrency(currency: Currency) {
        return dao.deleteCurrency(currency)
    }
}