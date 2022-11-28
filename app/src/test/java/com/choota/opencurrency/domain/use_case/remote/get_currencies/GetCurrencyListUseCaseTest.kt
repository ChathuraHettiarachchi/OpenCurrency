package com.choota.opencurrency.domain.use_case.remote.get_currencies

import app.cash.turbine.test
import com.choota.opencurrency.data.repository.FakeOpenCurrencyRepository
import com.choota.opencurrency.utils.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCurrencyListUseCaseTest {

    private lateinit var useCaseError: GetCurrencyListUseCase
    private lateinit var useCaseSuccess: GetCurrencyListUseCase

    @Before
    fun setUp() {
        useCaseError = GetCurrencyListUseCase(FakeOpenCurrencyRepository().apply { setShouldReturnNetworkError(true) })
        useCaseSuccess = GetCurrencyListUseCase(FakeOpenCurrencyRepository().apply { setShouldReturnNetworkError(false) })
    }

    @Test
    fun `verify Currency state change from loading to error on API or any error`() = runBlocking{
        useCaseError.invoke().test {
            val emitLoading = awaitItem()
            assertThat(emitLoading).isInstanceOf(Resource.Loading::class.java)

            val emitError = awaitItem()
            assertThat(emitError).isInstanceOf(Resource.Error::class.java)

            awaitComplete()
        }
    }

    @Test
    fun `verify Currency state change from loading to success on API success`() = runBlocking{
        useCaseSuccess.invoke().test {
            val emitLoading = awaitItem()
            assertThat(emitLoading).isInstanceOf(Resource.Loading::class.java)

            val emitSuccess = awaitItem()
            assertThat(emitSuccess).isInstanceOf(Resource.Success::class.java)

            awaitComplete()
        }
    }

    @Test
    fun `verify Currency API success return Currency data`() = runBlocking{
        useCaseSuccess.invoke().test {
            val emitLoading = awaitItem()
            assertThat(emitLoading).isInstanceOf(Resource.Loading::class.java)

            val emitSuccess = awaitItem()
            assertThat(emitSuccess).isInstanceOf(Resource.Success::class.java)

            assertThat(emitSuccess.data!!.size>1).isTrue()
            assertThat(emitSuccess.data!![1].code.isNotEmpty()).isTrue()

            awaitComplete()
        }
    }

}