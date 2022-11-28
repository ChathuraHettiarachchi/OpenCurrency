package com.choota.opencurrency.utils

import com.chibatching.kotpref.KotprefModel

object AppDefault: KotprefModel() {
    var lastSyncTime by longPref(0)
}