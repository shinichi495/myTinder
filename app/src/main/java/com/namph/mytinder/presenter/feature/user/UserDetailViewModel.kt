package com.namph.mytinder.presenter.feature.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.namph.mytinder.base.BaseViewModel
import com.namph.mytinder.domain.model.User
import com.namph.mytinder.domain.usecase.user.GetUserUseCase
import com.namph.mytinder.domain.usecase.base.Error
import com.namph.mytinder.domain.usecase.base.Result
import com.namph.mytinder.domain.usecase.user.SaveUserLocalUseCase
import com.namph.mytinder.mapper.map
import com.namph.mytinder.model.UserItem
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel

@ObsoleteCoroutinesApi
class UserDetailViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserLocalUseCase: SaveUserLocalUseCase
) : BaseViewModel() {

    override var receiveChannel: ReceiveChannel<Result<Any, Error>> = getUserUseCase.receiveChannel

    override fun resolve(value: Result<Any, Error>) {
        value.handleResult(::handleSuccess, ::handleFail, ::handleState)
    }

    private val _item = MutableLiveData<List<UserItem>>().apply { value = null }
    val item = _item

    fun requestUserFromServer() {
        getUserUseCase(GetUserUseCase.GET_USER_SERVER)
    }

    fun saveUser (user: User) {
        saveUserLocalUseCase(user)
    }

    fun requestUserFromLocal() {
        getUserUseCase(GetUserUseCase.GET_USER_LOCAL)
    }

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading

    fun handleSuccess(data: Any) {
        val users = data as List<User>
        if (users.size > 0) {
            val userItem = users.map {
                it.map()
            }
            _item.postValue(userItem)
        }
    }

    fun handleFail(err: Error) {
        Log.d("Namph", err.toString())
    }

    fun handleState(state: Result.State) {
    }
}