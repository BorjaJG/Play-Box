package com.example.playbox

import android.app.Application
import com.example.playbox.app.di.AppContainer

class PlayBoxApplication : Application() {
    val container by lazy { AppContainer(this) }
}
