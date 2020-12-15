package com.namph.mytinder.di

fun configurationTestAppComponent (baseApi : String) = listOf(
    mockWebService,
    configurationNetworkModuleForTest(baseApi))