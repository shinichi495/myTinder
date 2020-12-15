package com.namph.mytinder.domain.usecase.base

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

typealias SimpleResult = Result<Any, Error>
abstract class BaseUseCase<in Params> : CoroutineScope {
    private val parentJob = SupervisorJob()
    private val mainDispathcer = Dispatchers.Main
    var backgroundDispatcher = Dispatchers.IO
    protected val resultChannel = Channel<SimpleResult>()

    val receiveChannel : ReceiveChannel<SimpleResult> = resultChannel
    override var coroutineContext: CoroutineContext = parentJob + mainDispathcer

    protected abstract suspend fun run (params: Params)

    operator fun invoke (params: Params) {
        launch {
            withContext(backgroundDispatcher) {
                run(params)
            }
        }
    }
    protected fun <T> startAsync(block : suspend () -> T) : Deferred<T> = async(parentJob) {
        block()
    }

    fun clear () {
        resultChannel.close()
        parentJob.cancel()
    }
}