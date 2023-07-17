package com.utsman.network

import com.utsman.core.StateEvent
import io.ktor.client.plugins.ClientRequestException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Reducer<T> {
    private val _dataFlow: MutableStateFlow<StateEvent<T>> = MutableStateFlow(StateEvent.Idle())
    val dataFlow: StateFlow<StateEvent<T>> get() = _dataFlow

    suspend fun <U> transform(call: suspend () -> U, mapper: (U) -> T) {
        _dataFlow.value = StateEvent.Loading()
        val resultEvent: StateEvent<T> = try {
            val dataSuccess = call.invoke()
            val dataResult = mapper.invoke(dataSuccess)
            StateEvent.Success(dataResult)
        } catch (e: ClientRequestException) {
            e.printStackTrace()
            StateEvent.Failure(e)
        } catch (e: Exception) {
            e.printStackTrace()
            StateEvent.Failure(e)
        }
        _dataFlow.value = resultEvent
    }
}