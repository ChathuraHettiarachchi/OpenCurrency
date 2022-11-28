package com.choota.opencurrency.data.repository.remote


import com.choota.opencurrency.common.Constants.API_KEY
import com.choota.opencurrency.data.remote.OpenCurrencyAPI
import com.choota.opencurrency.data.remote.dto.CurrencyDto
import com.choota.opencurrency.data.remote.dto.RateDto
import com.choota.opencurrency.domain.repository.remote.OpenCurrencyRepository
import javax.inject.Inject

class OpenCurrencyRepositoryImpl @Inject constructor(private val api: OpenCurrencyAPI):
    OpenCurrencyRepository {
    override suspend fun getRates(appId: String): RateDto {
        return api.getRates(API_KEY)
    }

    override suspend fun getCurrencies(appId: String): CurrencyDto {
        return api.getCurrencies(API_KEY)
    }
}