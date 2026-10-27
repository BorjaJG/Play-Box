package com.example.playbox

import android.app.Application
import com.example.playbox.app.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PlayBoxApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PlayBoxApplication)
            modules(appModules)
        }
    }
}
