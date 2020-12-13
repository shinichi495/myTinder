package com.namph.mytinder.data.datasource.user

import com.namph.mytinder.domain.model.User
import com.namph.mytinder.domain.usecase.base.Error
import com.namph.mytinder.domain.usecase.base.Result


interface UserLocalDataSource {
    suspend fun getUsers() : Result<List<User>, Error>
    suspend fun insert(user : User , done : () -> Unit)
}