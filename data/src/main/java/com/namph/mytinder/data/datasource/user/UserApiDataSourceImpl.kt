package com.namph.mytinder.data.datasource.user

import com.namph.mytinder.data.service.UserService
import com.namph.mytinder.domain.model.User
import com.namph.mytinder.domain.usecase.base.Error
import com.namph.mytinder.domain.usecase.base.Result
import java.lang.Exception

class UserApiDataSourceImpl(val userApi : UserService) : UserApiDataSource {
    override suspend fun getUser(): Result<List<User>, Error> {
        try {
            val response = userApi.getUser()
            if (response.isSuccessful && response.body() != null) {
                return Result.Success(response.body()!!.results.map {
                    return@map User(
                        it.user.SSN,
                        it.user.gender,

                        it.user.name.title,
                        it.user.name.first,
                        it.user.name.last,

                        it.user.location.street,
                        it.user.location.city,
                        it.user.location.state,
                        it.user.location.zip,

                        it.user.email,
                        it.user.username,
                        it.user.phone,
                        it.user.cell,
                        it.user.picture
                    )
                })
            } else {
                return Result.Failure(Error.ResponseError)
            }
        } catch (err: Exception) {
            return Result.Failure(Error.NetworkError)
        }
    }
}