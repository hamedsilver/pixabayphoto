package com.example.pixabayphoto

import android.app.Application
import com.example.pixabayphoto.di.AppContainer

/**
 * Application class.
 */
class MainApplication : Application() {
    // Instance of AppContainer that will be used by all the Fragments of the app
    val appContainer = AppContainer(this)
}
