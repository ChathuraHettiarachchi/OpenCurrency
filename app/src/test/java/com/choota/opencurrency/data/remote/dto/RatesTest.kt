package com.choota.opencurrency.data.remote.dto

import com.choota.opencurrency.common.Constants
import com.google.common.truth.Truth
import com.google.gson.Gson
import org.junit.Test

class RatesTest{
    @Test
    fun `verify RateyDto can generate currencyList isTrue`(){
        val data = Gson().fromJson(Constants.rates, RateDto::class.java)
        Truth.assertThat(data != null).isTrue()
        Truth.assertThat(data.asRateList().isNotEmpty()).isTrue()
    }

    @Test
    fun `verify RateDto rates are getting added isTrue`(){
        val data = Gson().fromJson(Constants.rates, RateDto::class.java)
        Truth.assertThat(data != null).isTrue()
        Truth.assertThat(data.asRateList()[0].rate > 0).isTrue()
    }
}