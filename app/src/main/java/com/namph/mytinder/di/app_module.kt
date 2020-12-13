package com.namph.mytinder.di

import android.app.Application
import androidx.room.Room
import com.namph.mytinder.data.datasource.user.UserApiDataSource
import com.namph.mytinder.data.datasource.user.UserApiDataSourceImpl
import com.namph.mytinder.data.datasource.user.UserLocalDataSource
import com.namph.mytinder.data.datasource.user.UserLocalDataSourceImpl
import com.namph.mytinder.data.db.MyTiderDb
import com.namph.mytinder.data.db.user.UserDao
import org.koin.core.qualifier.named
import org.koin.dsl.module

import com.namph.mytinder.data.repository.UserRepositoryImpl
import com.namph.mytinder.data.service.UserService
import com.namph.mytinder.domain.repository.UserRepository
import com.namph.mytinder.domain.usecase.user.GetUserUseCase
import com.namph.mytinder.domain.usecase.user.SaveUserLocalUseCase
import com.namph.mytinder.presenter.feature.user.UserDetailViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel

val repositoryModules = module {
    fun provideUserRepository(
        apiSource: UserApiDataSource,
        locaSource: UserLocalDataSource
    ): UserRepository {
        return UserRepositoryImpl(apiSource, locaSource)
    }
    single(named("remote")) {
        provideUserRepository(
            get(named(GET_USER_API_DATA_SOURCE)),
            get(named(GET_USER_LOCAL_DATA_SOURCE))
        )
    }
}
val usecaseModules = module {
    factory(named(GET_USER_USECASE)) {
        GetUserUseCase(userRepository = get(named("remote")))
    }
    factory(named(SAVE_USER_USECASE)) {
        SaveUserLocalUseCase(userRepository = get(named("remote")))
    }
}

val networkModule = module {
    single(named(OKHTTP_INSTANCE)) { createOkHttpClient() }
    single(named(API)) { createService<UserService>(get(named(OKHTTP_INSTANCE)), BASE_URL) }
}

val databaseModule = module {
    fun provideDB(application: Application) = Room.databaseBuilder(application,MyTiderDb::class.java,"MyTinderDb").build()
    fun provideUserDao(db : MyTiderDb) = db.userDao()
    single (named(MY_TINDER_DB)) { provideDB(androidApplication()) }
    single(named(USER_DB)) { provideUserDao(get(named(MY_TINDER_DB)))  }
}


val viewModels = module {
    viewModel {
        UserDetailViewModel(
            get(named(GET_USER_USECASE)),
            get(named(SAVE_USER_USECASE))
        )
    }
}

val dataSourceModule = module {
    fun provideUserApiDataSource(userApi: UserService): UserApiDataSource {
        return UserApiDataSourceImpl(userApi = userApi)
    }

    fun provideUserLocalDataSource(userDao: UserDao): UserLocalDataSource {
        return UserLocalDataSourceImpl(userDao = userDao)
    }
    single(named(GET_USER_API_DATA_SOURCE)) {
        provideUserApiDataSource(get(named(API)))
    }

    single(named(GET_USER_LOCAL_DATA_SOURCE)) {
        provideUserLocalDataSource(get(named(USER_DB)))
    }
}
private const val API = "Api"
private const val OKHTTP_INSTANCE = "OkHttp"

private const val GET_USER_USECASE = "getUserUseCase"
private const val SAVE_USER_USECASE = "saveUserUseCase"

private const val GET_USER_API_DATA_SOURCE = "getUserApiDataSource"
private const val GET_USER_LOCAL_DATA_SOURCE = "getUserLocalDataSource"

private const val MY_TINDER_DB = "MyTinDer_BD"
private const val USER_DB = "User_DB"

private const val BASE_URL = "https://randomuser.me/"



