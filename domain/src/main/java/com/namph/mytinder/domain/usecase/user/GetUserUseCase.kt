package com.namph.mytinder.domain.usecase.user

import com.namph.mytinder.domain.repository.UserRepository
import com.namph.mytinder.domain.usecase.base.BaseUseCase
import com.namph.mytinder.domain.usecase.base.Result

class GetUserUseCase(private val userRepository: UserRepository) : BaseUseCase<Int>() {

    
    private suspend fun getUserFromServer () {
        resultChannel.send(Result.State.Loading)
        resultChannel.send(userRepository.getUserInfor())
        resultChannel.send(Result.State.Loaded)
    }

    private suspend fun getUserFromLocal () {
        resultChannel.send(Result.State.Loading)
        resultChannel.send(userRepository.getUsersFromLocal())
        resultChannel.send(Result.State.Loaded)
    }
    companion object {
        val GET_USER_LOCAL = 1
        val GET_USER_SERVER = 2
    }

    override suspend fun run(params: Int) {
        if (params == GET_USER_LOCAL) {
            getUserFromLocal()
        } else if (params == GET_USER_SERVER) {
            getUserFromServer()
        }
    }
}