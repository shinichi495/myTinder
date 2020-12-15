package com.namph.mytinder.data.datasource.user

import com.namph.mytinder.domain.model.User
import com.namph.mytinder.domain.usecase.base.Error
import com.namph.mytinder.domain.usecase.base.Result

suspend fun getUser (
    apiSource : UserApiDataSource
) {
    apiSource.getUser()
}
interface UserApiDataSource  {
    suspend fun getUser() : Result<List<User>,Error>
}