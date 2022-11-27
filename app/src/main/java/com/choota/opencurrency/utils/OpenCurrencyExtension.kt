package com.choota.opencurrency.utils

import com.choota.opencurrency.domain.model.Currency

fun Double.round2Decimal() : String {
    return "%.2f".format(this)
}

fun List<Currency>.reCalculate(selection: String, newAmount: Double): List<Currency> {
    val rateForOneUSD = this.last{it.code.lowercase() == selection.lowercase()}.rate
    this.forEach {
        it.apply {
            amount = newAmount * (1/rateForOneUSD) * rate
        }
    }
    return this
}