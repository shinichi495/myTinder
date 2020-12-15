package com.namph.mytinder.base

import androidx.lifecycle.ViewModel
import com.namph.mytinder.domain.usecase.base.Error
import com.namph.mytinder.domain.usecase.base.Result
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext

@ObsoleteCoroutinesApi
abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private val job = Job()
    protected abstract var receiveChannel: ReceiveChannel<Result<Any, Error>>
    override var coroutineContext: CoroutineContext = job + Dispatchers.Main

    abstract fun resolve(value: Result<Any, Error>)

    init {
        processStream()
    }

    private fun processStream() {
        launch {
            receiveChannel.consumeEach {
                resolve(it)
            }
        }
    }

    override fun onCleared() {
        receiveChannel.cancel()
        coroutineContext.cancel()
        super.onCleared()
    }

}