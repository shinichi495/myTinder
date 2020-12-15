package com.namph.mytinder.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.namph.mytinder.data.datasource.user.UserApiDataSource
import com.namph.mytinder.data.datasource.user.UserApiDataSourceImpl
import com.namph.mytinder.data.service.UserService
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun configurationNetworkModuleForTest(baseUrl: String) = module {
    single {
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
    }
    factory { get<Retrofit>().create(UserService::class.java) }
}




