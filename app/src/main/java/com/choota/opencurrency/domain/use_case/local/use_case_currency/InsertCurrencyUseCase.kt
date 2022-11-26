package com.choota.opencurrency.domain.use_case.local.use_case_currency

import com.choota.opencurrency.domain.model.Currency
import com.choota.opencurrency.domain.repository.local.CurrencyRepository
import javax.inject.Inject

class InsertCurrencyUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend operator fun invoke(currency: Currency): Long{
        return repository.insertCurrency(currency)
    }
}