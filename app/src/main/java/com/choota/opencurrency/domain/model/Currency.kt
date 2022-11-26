package com.choota.opencurrency.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currency(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var code: String = "",
    var name: String = "",
    var rate: Double = 0.0,
)
