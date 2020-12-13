package com.namph.mytinder.domain.usecase.user

import com.namph.mytinder.domain.model.User
import com.namph.mytinder.domain.repository.UserRepository
import com.namph.mytinder.domain.usecase.base.BaseUseCase

class SaveUserLocalUseCase(private val userRepository: UserRepository) : BaseUseCase<User>() {

    override suspend fun run(params: User) {
        userRepository.saveUser(params)
    }
}