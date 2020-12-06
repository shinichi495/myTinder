package com.namph.mytinder.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.namph.mytinder.base.BaseViewModel
import com.namph.mytinder.domain.model.User
import com.namph.mytinder.domain.usecase.GetUserUseCase
import com.namph.mytinder.domain.usecase.base.Error
import com.namph.mytinder.domain.usecase.base.Result
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel

@ObsoleteCoroutinesApi
class UserDetailViewModel(private val getUserUseCase: GetUserUseCase) : BaseViewModel() {
    override val receiveChannel: ReceiveChannel<Result<Any, Error>>
        get() = getUserUseCase.receiveChannel

    override fun resolve(value: Result<Any, Error>) {
        value.handleResult(::handleSuccess, ::handleFail, ::handleState)
    }

    private val _item = MutableLiveData<List<User>>().apply { value = null }
    val item = _item

    fun requestUser () {
        getUserUseCase(null)
    }
    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading

    fun handleSuccess(data: Any) {
        Log.d("namph", "success")
        _item.postValue((data as List<User>))
    }
    fun handleFail(err: Error) {
        Log.d("namph", "error")
    }
    fun handleState(state: Result.State) {
        Log.d("namph", "state :$state")
    }
}