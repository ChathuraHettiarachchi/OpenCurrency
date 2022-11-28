package com.choota.opencurrency.domain.use_case.local.use_case_currency

import com.choota.opencurrency.domain.model.Currency
import com.choota.opencurrency.domain.repository.local.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    operator fun invoke(): Flow<List<Currency>> {
        return repository.getCurrencies()
    }
}