package com.choota.opencurrency.domain.repository

import com.choota.opencurrency.data.remote.dto.CurrencyDto
import com.choota.opencurrency.data.remote.dto.RateDto

interface OpenCurrencyRepository {
    suspend fun getRates(appId: String = ""): RateDto
    suspend fun getCurrencies(appId: String = ""): CurrencyDto
}