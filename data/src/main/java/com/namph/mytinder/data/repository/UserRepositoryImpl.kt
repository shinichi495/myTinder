package com.namph.mytinder.data.repository

import com.namph.mytinder.data.datasource.user.UserApiDataSource
import com.namph.mytinder.data.datasource.user.UserLocalDataSource
import com.namph.mytinder.domain.model.User
import com.namph.mytinder.domain.repository.UserRepository
import com.namph.mytinder.domain.usecase.base.Error
import com.namph.mytinder.domain.usecase.base.Result

class UserRepositoryImpl(val apiSource: UserApiDataSource, val localSource: UserLocalDataSource) :
    UserRepository {

    override suspend fun getUserInfor(): Result<List<User>, Error> {
        return apiSource.getUser()
    }

    override suspend fun saveUser(user: User) {
        localSource.insert(user,{})
    }

    override suspend fun getUsersFromLocal(): Result<List<User>, Error> {
        return localSource.getUsers()
    }

}