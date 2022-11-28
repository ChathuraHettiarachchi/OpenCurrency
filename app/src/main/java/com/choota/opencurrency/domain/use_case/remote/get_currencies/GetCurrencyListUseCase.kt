package com.choota.opencurrency.domain.use_case.remote.get_currencies

import com.choota.opencurrency.data.remote.dto.asList
import com.choota.opencurrency.domain.model.Currency
import com.choota.opencurrency.domain.repository.remote.OpenCurrencyRepository
import com.choota.opencurrency.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import javax.inject.Inject

class GetCurrencyListUseCase @Inject constructor(private val repository: OpenCurrencyRepository) {
    operator fun invoke(): Flow<Resource<List<Currency>>> = flow {
        try {
            emit(Resource.Loading<List<Currency>>())
            val data = repository.getCurrencies()
            val list = data.asList()
            emit(Resource.Success<List<Currency>>(list))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<Currency>>(
                    e.localizedMessage ?: "There is an exception occurred on HTTP Connection"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error<List<Currency>>(
                    e.localizedMessage
                        ?: "Couldn't connect to server. Please check the network connection"
                )
            )
        } catch (e: SocketException) {
            emit(
                Resource.Error<List<Currency>>(
                    e.localizedMessage ?: "There is an exception occurred on Socket Connection"
                )
            )
        }
    }
}