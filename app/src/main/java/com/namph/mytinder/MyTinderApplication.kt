package com.namph.mytinder

import android.app.Application
import com.namph.mytinder.di.networkModule
import com.namph.mytinder.di.repositoryModules
import com.namph.mytinder.di.usecaseModules
import com.namph.mytinder.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyTinderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyTinderApplication)
            modules(
                viewModels + repositoryModules + usecaseModules + networkModule
            )
        }
        instance = this
    }
    companion object {
        lateinit var instance : MyTinderApplication
            private set
    }
}