package com.choota.opencurrency.utils

fun Double.round2Decimal() : String {
    return "%.2f".format(this)
}