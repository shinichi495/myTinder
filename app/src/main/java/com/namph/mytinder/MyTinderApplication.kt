package com.namph.mytinder

import androidx.multidex.MultiDexApplication
import com.namph.mytinder.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyTinderApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyTinderApplication)
            modules(
                viewModels + repositoryModules + databaseModule + dataSourceModule  + usecaseModules + networkModule
            )
        }
        instance = this
    }
    companion object {
        lateinit var instance : MyTinderApplication
            private set
    }
}