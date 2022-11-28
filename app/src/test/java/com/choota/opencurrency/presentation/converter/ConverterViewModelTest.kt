package com.choota.opencurrency.presentation.converter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.blongho.country_data.World
import com.choota.opencurrency.MainCoroutineRule
import com.choota.opencurrency.data.repository.FakeOpenCurrencyRepository
import com.choota.opencurrency.data.repository.local.FakeCurrencyRepository
import com.choota.opencurrency.domain.use_case.local.use_case_currency.GetCurrenciesUseCase
import com.choota.opencurrency.domain.use_case.local.use_case_currency.InsertCurrencyUseCase
import com.choota.opencurrency.domain.use_case.remote.get_currencies.GetCurrencyListUseCase
import com.choota.opencurrency.domain.use_case.remote.get_rates.GetRateListUseCase
import com.choota.opencurrency.utils.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ConverterViewModelTest {
    private lateinit var viewModelError: ConverterViewModel
    private lateinit var viewModelSuccess: ConverterViewModel

    private lateinit var useCurrencyCaseError: GetCurrencyListUseCase
    private lateinit var useCurrencyCaseSuccess: GetCurrencyListUseCase

    private lateinit var useRateCaseError: GetRateListUseCase
    private lateinit var useRateCaseSuccess: GetRateListUseCase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        useCurrencyCaseSuccess = GetCurrencyListUseCase(FakeOpenCurrencyRepository().apply {
            setShouldReturnNetworkError(false)
        })
        useCurrencyCaseError = GetCurrencyListUseCase(FakeOpenCurrencyRepository().apply {
            setShouldReturnNetworkError(true)
        })

        useRateCaseSuccess = GetRateListUseCase(FakeOpenCurrencyRepository().apply {
            setShouldReturnNetworkError(false)
        })
        useRateCaseError =
            GetRateListUseCase(FakeOpenCurrencyRepository().apply { setShouldReturnNetworkError(true) })

        viewModelError = ConverterViewModel(
            useCurrencyCaseError, useRateCaseError, GetCurrenciesUseCase(FakeCurrencyRepository()),
            InsertCurrencyUseCase(FakeCurrencyRepository())
        )

        viewModelSuccess = ConverterViewModel(
            useCurrencyCaseSuccess,
            useRateCaseSuccess,
            GetCurrenciesUseCase(FakeCurrencyRepository()),
            InsertCurrencyUseCase(FakeCurrencyRepository())
        )
    }

    @Test
    fun `error on getCurrencies isTrue`() {
        val result = viewModelError.currencyState.value
        assertThat(result.error.isNotBlank()).isTrue()
    }

    @Test
    fun `success on getCurrencies isTrue`() = runBlocking {
        useCurrencyCaseSuccess.invoke().test {
            val emitLoading = awaitItem()
            assertThat(emitLoading).isInstanceOf(Resource.Loading::class.java)

            val emitSuccess = awaitItem()
            assertThat(emitSuccess).isInstanceOf(Resource.Success::class.java)
            //assertThat(!viewModelSuccess.currencyState.value.isLoading).isTrue()
            viewModelSuccess.currencyState.collectLatest {
                if (!it.isLoading)
                    assertThat(viewModelSuccess.currencyState.value.data.isNotEmpty()).isTrue()
            }

            awaitComplete()

            assertThat(viewModelSuccess.currencyState.value.data.isNotEmpty()).isTrue()
        }
    }

    @Test
    fun `success on getCurrencies and currency is attached with code`() = runTest {
        useCurrencyCaseSuccess().test {
            val emitLoading1 = awaitItem()
            assertThat(emitLoading1).isInstanceOf(Resource.Loading::class.java)
            assertThat(viewModelSuccess.currencyState.value.isLoading).isTrue()

            dispatcher.scheduler.advanceUntilIdle()

            val emitSuccess1 = awaitItem()
            assertThat(emitSuccess1).isInstanceOf(Resource.Success::class.java)
            assertThat(emitSuccess1.data?.isNotEmpty()).isTrue()
            assertThat(emitSuccess1.data!![0].code.isNotEmpty()).isTrue()

            dispatcher.scheduler.advanceUntilIdle()

            awaitComplete()
        }
    }
}