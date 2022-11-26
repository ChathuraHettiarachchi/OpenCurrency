package com.choota.opencurrency.presentation.converter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    init {
        getCurrencyList()
    }

    private fun getCurrencyList() {
        val isExpired = (Date().time - AppDefault.lastSyncTime) > SYNC_PERIOD
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
                        localInsertCurrencyUseCase(
                            Currency(
                                0,
                                filtered.first().code,
                                filtered.first().name,
                                rate.rate
                            )
                        )
                    }
                    AppDefault.lastSyncTime = Date().time

                    localCurrencyUseCase().collect {
                        _currencyState.value = CurrencyDataState(isLoading = false, data = it)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}