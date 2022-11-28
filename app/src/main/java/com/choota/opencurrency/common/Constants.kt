package com.choota.opencurrency.common

import com.blongho.country_data.Country

object Constants {
    const val BASE_URL = "https://openexchangerates.org/api/"
    const val API_KEY = "c99cf2b9ba6147b0a94805fab2805ae0"

    const val DATABASE = "OpenCurrency"
    const val SYNC_PERIOD: Long = 1800000

    fun isTestMode(): Boolean {
        val result: Boolean = try {
            Class.forName("com.choota.opencurrency.presentation.converter.ConverterViewModelTest")
            true
        } catch (e: Exception) {
            false
        }
        return result
    }
}