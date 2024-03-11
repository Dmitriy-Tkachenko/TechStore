package ru.tk4dmitriy.techstore

import android.app.Application
import ru.tk4dmitriy.techstore.di.AppComponent
import ru.tk4dmitriy.techstore.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}