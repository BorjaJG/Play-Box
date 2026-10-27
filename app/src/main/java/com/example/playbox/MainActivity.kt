package com.example.playbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.playbox.app.PlayBoxApp
import com.example.playbox.core.di.AppContainer
import com.example.playbox.core.presentation.theme.PlayBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val container = (application as PlayBoxApplication).container
        setContent { PlayBoxTheme { PlayBoxApp(container) } }
    }
}
