package com.choota.opencurrency.domain.use_case.remote.get_rates

import app.cash.turbine.test
import com.choota.opencurrency.data.repository.FakeOpenCurrencyRepository
import com.choota.opencurrency.domain.use_case.remote.get_currencies.GetCurrencyListUseCase
import com.choota.opencurrency.utils.Resource
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetRateListUseCaseTest{

    private lateinit var useCaseError: GetRateListUseCase
    private lateinit var useCaseSuccess: GetRateListUseCase

    @Before
    fun setUp() {
        useCaseError = GetRateListUseCase(FakeOpenCurrencyRepository().apply { setShouldReturnNetworkError(true) })
        useCaseSuccess = GetRateListUseCase(FakeOpenCurrencyRepository().apply { setShouldReturnNetworkError(false) })
    }

    @Test
    fun `verify Rate state change from loading to error on API or any error`() = runBlocking{
        useCaseError.invoke().test {
            val emitLoading = awaitItem()
            Truth.assertThat(emitLoading).isInstanceOf(Resource.Loading::class.java)

            val emitError = awaitItem()
            Truth.assertThat(emitError).isInstanceOf(Resource.Error::class.java)

            awaitComplete()
        }
    }

    @Test
    fun `verify Rate state change from loading to success on API success`() = runBlocking{
        useCaseSuccess.invoke().test {
            val emitLoading = awaitItem()
            Truth.assertThat(emitLoading).isInstanceOf(Resource.Loading::class.java)

            val emitSuccess = awaitItem()
            Truth.assertThat(emitSuccess).isInstanceOf(Resource.Success::class.java)

            awaitComplete()
        }
    }

    @Test
    fun `verify Rate API success return Rate data`() = runBlocking{
        useCaseSuccess.invoke().test {
            val emitLoading = awaitItem()
            Truth.assertThat(emitLoading).isInstanceOf(Resource.Loading::class.java)

            val emitSuccess = awaitItem()
            Truth.assertThat(emitSuccess).isInstanceOf(Resource.Success::class.java)

            Truth.assertThat(emitSuccess.data!!.size>1).isTrue()
            Truth.assertThat(emitSuccess.data!![1].rate > 0).isTrue()

            awaitComplete()
        }
    }
}