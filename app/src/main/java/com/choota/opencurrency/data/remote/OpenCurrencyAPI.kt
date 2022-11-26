package com.choota.opencurrency.data.remote

import com.choota.opencurrency.data.remote.dto.CurrencyDto
import com.choota.opencurrency.data.remote.dto.RateDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenCurrencyAPI {
    @GET("/latest.json")
    suspend fun getRates(
        @Query("app_id") appId: String
    ): RateDto

    @GET("/currencies.json")
    suspend fun getCurrencies(
        @Query("app_id") appId: String
    ): CurrencyDto
}