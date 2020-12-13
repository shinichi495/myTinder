package com.namph.mytinder.data.datasource.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.namph.mytinder.data.db.user.UserDao
import com.namph.mytinder.data.mapper.map
import com.namph.mytinder.domain.model.User
import com.namph.mytinder.domain.usecase.base.Error
import com.namph.mytinder.domain.usecase.base.Result
import java.lang.Exception

class UserLocalDataSourceImpl(private val userDao : UserDao)  : UserLocalDataSource {
    override suspend fun getUsers(): Result<List<User>, Error> {
        try {
            val local = userDao.selectAllPage()
            val response = local.map {
                it.map()
            }
            return Result.Success(response)
        } catch (err : Exception) {
            return Result.Failure(Error.LocalDataError)
        }
    }

    override suspend fun insert(user: User, done: () -> Unit) {
        userDao.insert(user.map())
        done()
    }
}