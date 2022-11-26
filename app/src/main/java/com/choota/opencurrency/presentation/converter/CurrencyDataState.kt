package com.choota.opencurrency.presentation.converter

import com.choota.opencurrency.domain.model.Currency

/**
 * State for popular channel list
 */
data class CurrencyDataState(
    var isLoading: Boolean = false,
    var data: List<Currency> = emptyList(),
    var error: String = ""
)
