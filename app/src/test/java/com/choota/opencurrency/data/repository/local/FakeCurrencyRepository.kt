package com.choota.opencurrency.data.repository.local

import com.choota.opencurrency.common.Constants
import com.choota.opencurrency.domain.model.Currency
import com.choota.opencurrency.domain.repository.local.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCurrencyRepository : CurrencyRepository {
    override fun getCurrencies(): Flow<List<Currency>> {
        return flow{Constants.currencyList()}
    }

    override suspend fun getCurrencyById(id: Long): Currency {
        return Currency()
    }

    override suspend fun getCurrencyByTag(code: String): Currency {
        return Currency()
    }

    override suspend fun insertCurrency(currency: Currency): Long {
        return 0
    }

    override suspend fun updateCurrency(currency: Currency) {
    }

    override suspend fun deleteCurrency(currency: Currency) {
    }

    override suspend fun countByCode(code: String): Long {
        return 0
    }
}