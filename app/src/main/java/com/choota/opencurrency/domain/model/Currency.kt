package com.choota.opencurrency.domain.model

data class Currency(
    var id: Long,
    var code: String,
    var name: String,
    var rate: Double,
)
