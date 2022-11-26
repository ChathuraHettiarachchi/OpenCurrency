package com.choota.opencurrency.domain.use_case.remote.get_rates

import com.choota.opencurrency.data.remote.dto.asRateList
import com.choota.opencurrency.domain.model.Rate
import com.choota.opencurrency.domain.repository.remote.OpenCurrencyRepository
import com.choota.opencurrency.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import javax.inject.Inject

class GetRateListUseCase @Inject constructor(private val repository: OpenCurrencyRepository) {
    operator fun invoke(): Flow<Resource<List<Rate>>> = flow {
        try {
            emit(Resource.Loading<List<Rate>>())
            val data = repository.getRates()
            val list = data.asRateList()
            emit(Resource.Success<List<Rate>>(list))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<Rate>>(
                    e.localizedMessage ?: "There is an exception occurred on HTTP Connection"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error<List<Rate>>(
                    e.localizedMessage
                        ?: "Couldn't connect to server. Please check the network connection"
                )
            )
        } catch (e: SocketException) {
            emit(
                Resource.Error<List<Rate>>(
                    e.localizedMessage ?: "There is an exception occurred on Socket Connection"
                )
            )
        }
    }
}