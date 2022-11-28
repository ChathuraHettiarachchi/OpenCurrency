package com.choota.opencurrency.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class OpenCurrencyExtensionKtTest {
    @Test
    fun `verify double value round to two decimal places`() {
        val input: Double = 100.12345
        val outPut: Double = 100.12

        assertThat(input.round2Decimal() == outPut.toString()).isTrue()
    }

    @Test
    fun `verify double value round to two decimal places with third having over 5`() {
        val input: Double = 100.12645
        val outPut: Double = 100.13

        assertThat(input.round2Decimal() == outPut.toString()).isTrue()
    }
}