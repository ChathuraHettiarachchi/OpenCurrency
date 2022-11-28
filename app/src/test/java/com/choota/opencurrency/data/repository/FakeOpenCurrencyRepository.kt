package com.choota.opencurrency.data.repository

import com.choota.opencurrency.common.Constants
import com.choota.opencurrency.data.remote.dto.CurrencyDto
import com.choota.opencurrency.data.remote.dto.RateDto
import com.choota.opencurrency.data.remote.dto.Rates
import com.choota.opencurrency.domain.repository.remote.OpenCurrencyRepository
import com.google.gson.Gson
import java.net.SocketException

class FakeOpenCurrencyRepository : OpenCurrencyRepository {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getRates(appId: String): RateDto {
        if (shouldReturnNetworkError) {
            throw SocketException("Unable to get the data object from server")
        } else {
            return Gson().fromJson(Constants.rates, RateDto::class.java)
        }
    }

    override suspend fun getCurrencies(appId: String): CurrencyDto {
        if (shouldReturnNetworkError) {
            throw SocketException("Unable to get the data object from server")
        } else {
            return Gson().fromJson(Constants.currencies, CurrencyDto::class.java)
        }
    }

}