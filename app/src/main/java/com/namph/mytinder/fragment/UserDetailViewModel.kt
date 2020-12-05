package com.namph.mytinder.fragment

import com.namph.mytinder.base.BaseViewModel
import com.namph.mytinder.domain.usecase.GetUserUseCase
import com.namph.mytinder.domain.usecase.base.Error
import com.namph.mytinder.domain.usecase.base.Result
import kotlinx.coroutines.channels.ReceiveChannel

class UserDetailViewModel(private val getUserUseCase: GetUserUseCase) : BaseViewModel() {
    override val receiveChannel: ReceiveChannel<Result<Any, Error>>
        get() = getUserUseCase.receiveChannel

    override fun resolve(value: Result<Any, Error>) {
        value.handleResult(::handleSuccess, ::handleFail, ::handleState)
    }

    fun handleSuccess(data: Any) {}
    fun handleFail(err: Error) {}
    fun handleState(state: Result.State) {}
}