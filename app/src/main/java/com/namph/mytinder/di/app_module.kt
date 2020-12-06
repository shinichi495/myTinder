package com.namph.mytinder.di

import org.koin.core.qualifier.named
import org.koin.dsl.module

import com.namph.mytinder.data.repository.UserRepositoryImpl
import com.namph.mytinder.data.service.UserService
import com.namph.mytinder.domain.repository.UserRepository
import com.namph.mytinder.domain.usecase.GetUserUseCase
import com.namph.mytinder.fragment.UserDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel

val repositoryModules = module {
    single (named("remote")) { UserRepositoryImpl(get(named(API))) as UserRepository }
}
val usecaseModules = module {
    factory (named(GET_NEWS_USECASE)) {
        GetUserUseCase(userRepository = get(named("remote")))
    }
}

val networkModule = module {
    single (named(OKHTTP_INSTANCE)) { createOkHttpClient() }
    single(named(API)) { createService<UserService>(get(named(OKHTTP_INSTANCE)), BASE_URL) }
}

val viewModels = module {
    viewModel {
        UserDetailViewModel(get(named(GET_NEWS_USECASE)))
    }
}
private const val API = "Api"
private const val OKHTTP_INSTANCE = "OkHttp"
private const val GET_NEWS_USECASE = "getNewsUseCase"
private const val BASE_URL = "https://randomuser.me/"


