package com.choota.opencurrency.data.remote.dto

import com.choota.opencurrency.common.Constants
import com.google.common.truth.Truth
import com.google.gson.Gson
import org.junit.Test

class CurrencyDtoKtTest {
    @Test
    fun `verify CurrencyDto can generate currencyList isTrue`(){
        val data = Gson().fromJson(Constants.currencies, CurrencyDto::class.java)
        Truth.assertThat(data != null).isTrue()
        Truth.assertThat(data.asList().isNotEmpty()).isTrue()
    }

    @Test
    fun `verify CurrencyDto rates are getting added isTrue`(){
        val data = Gson().fromJson(Constants.currencies, CurrencyDto::class.java)
        Truth.assertThat(data != null).isTrue()
        Truth.assertThat(data.asList()[0].code.isNotEmpty()).isTrue()
    }
}