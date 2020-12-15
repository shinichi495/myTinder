package com.namph.mytinder.di

import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module

val mockWebService = module {
    factory {
        MockWebServer()
    }
}