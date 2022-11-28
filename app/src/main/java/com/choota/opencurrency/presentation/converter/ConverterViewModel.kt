package com.choota.opencurrency.presentation.converter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blongho.country_data.Country
import com.blongho.country_data.World
import com.choota.opencurrency.common.Constants
import com.choota.opencurrency.common.Constants.SYNC_PERIOD
import com.choota.opencurrency.domain.model.Currency
import com.choota.opencurrency.domain.model.Rate
import com.choota.opencurrency.domain.use_case.local.use_case_currency.GetCurrenciesUseCase
import com.choota.opencurrency.domain.use_case.local.use_case_currency.InsertCurrencyUseCase
import com.choota.opencurrency.domain.use_case.local.use_case_currency.UpdateCurrencyUseCase
import com.choota.opencurrency.domain.use_case.remote.get_currencies.GetCurrencyListUseCase
import com.choota.opencurrency.domain.use_case.remote.get_rates.GetRateListUseCase
import com.choota.opencurrency.utils.AppDefault
import com.choota.opencurrency.utils.Resource
import com.choota.opencurrency.utils.reCalculate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val remoteCurrencyUseCase: GetCurrencyListUseCase,
    private val remoteRatesUseCase: GetRateListUseCase,
    private val localCurrencyUseCase: GetCurrenciesUseCase,
    private val localInsertCurrencyUseCase: InsertCurrencyUseCase
) : ViewModel() {

    // mutable state for api/db response
    private val _currencyState = MutableStateFlow<CurrencyDataState>(CurrencyDataState())
    val currencyState: StateFlow<CurrencyDataState> = _currencyState
    lateinit var currencies: List<Currency>

    // mutable state for api/db response
    private val _countries =
        if (Constants.isTestMode()) emptyList<Country>() else World.getAllCountries()
    private val _countriesState =
        MutableStateFlow<Country?>(if (_countries.isNotEmpty()) _countries[0] else null)
    val currentCountryState: StateFlow<Country?> = _countriesState

    init {
        getCurrencyList()
        _countriesState.value =
            if (_countries.isNotEmpty()) _countries.last { it.currency.code.lowercase() == "usd" } else null
    }

    private fun getCurrencyList() {
        val isExpired = if (!Constants.isTestMode())
            (Date().time - AppDefault.lastSyncTime) > SYNC_PERIOD
        else
            true

        if (isExpired) {
            // call remote if network is present
            remoteRatesUseCase().onEach { res ->
                when (res) {
                    is Resource.Error -> {
                        _currencyState.value = CurrencyDataState(
                            isLoading = false,
                            error = res.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _currencyState.value = CurrencyDataState(isLoading = true)
                    }
                    is Resource.Success -> {
                        getRemoteCurrencies(res.data)
                    }
                }
            }.launchIn(viewModelScope)
        } else {
            // load from local
            _currencyState.value = CurrencyDataState(isLoading = true)
            viewModelScope.launch(Dispatchers.IO) {
                localCurrencyUseCase().collect {
                    currencies = it
                    _currencyState.value = CurrencyDataState(isLoading = false, data = it)
                }
            }
        }

    }

    private fun getRemoteCurrencies(rates: List<Rate>?) {
        remoteCurrencyUseCase().onEach { res ->
            when (res) {
                is Resource.Error -> {
                    _currencyState.value = CurrencyDataState(
                        isLoading = false,
                        error = res.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _currencyState.value = CurrencyDataState(isLoading = true)
                }
                is Resource.Success -> {
                    rates!!.forEach { rate ->
                        val filtered = res.data!!.filter { currency -> currency.code == rate.code }

                        var flag: Int? = null
                        var symbol: String = ""
                        try {
                            val country = resolveCountry(filtered.first().code)
                            if (country != null && country.currency != null) {
                                flag = country.flagResource
                                symbol = country.currency.symbol
                            }
                        } catch (ignored: Exception) {
                        }

                        localInsertCurrencyUseCase(
                            Currency(
                                0,
                                filtered.first().code,
                                filtered.first().name,
                                rate.rate,
                                flag,
                                symbol
                            )
                        )
                    }
                    if (!Constants.isTestMode()) AppDefault.lastSyncTime = Date().time

                    localCurrencyUseCase().collect {
                        currencies = it
                        _currencyState.value = CurrencyDataState(isLoading = false, data = it)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun resolveCountry(filterCode: String): Country? {
        return if (!Constants.isTestMode()) {
            _countries.last { it.currency.code.lowercase() == filterCode.lowercase() }
        } else {
            null
        }
    }

    fun reCalculateCurrencyPairs(selectedCode: String, amount: Double) {
        _countriesState.value =
            _countries.last { it.currency.code.lowercase() == selectedCode.lowercase() }

        _currencyState.value = CurrencyDataState(isLoading = true)
        _currencyState.value =
            CurrencyDataState(isLoading = false, currencies.reCalculate(selectedCode, amount))
    }
}