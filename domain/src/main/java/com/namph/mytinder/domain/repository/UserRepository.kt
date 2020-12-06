package com.namph.mytinder.domain.repository

import com.namph.mytinder.domain.model.User
import com.namph.mytinder.domain.usecase.base.Error
import com.namph.mytinder.domain.usecase.base.Result

interface UserRepository {
    suspend fun getUserInfor () : Result<List<User>,Error>
}