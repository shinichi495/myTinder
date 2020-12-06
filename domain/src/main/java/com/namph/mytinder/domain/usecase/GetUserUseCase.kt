package com.namph.mytinder.domain.usecase

import com.namph.mytinder.domain.repository.UserRepository
import com.namph.mytinder.domain.usecase.base.BaseUseCase
import com.namph.mytinder.domain.usecase.base.Result

class GetUserUseCase (private val userRepository : UserRepository) : BaseUseCase<Nothing?>() {
//    override suspend fun run(params: Nothing) {
//        resultChannel.send(Result.State.Loading)
//        resultChannel.send(userRepository.getUserInfor())
//        resultChannel.send(Result.State.Loaded)
//    }

    override suspend fun run(params: Nothing?) {
        resultChannel.send(Result.State.Loading)
        resultChannel.send(userRepository.getUserInfor())
        resultChannel.send(Result.State.Loaded)    }
}