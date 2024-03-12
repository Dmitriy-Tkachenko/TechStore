package ru.tk4dmitriy.techstore.di

import dagger.Component
import ru.tk4dmitriy.techstore.ui.MainActivity
import javax.inject.Singleton

@Component(modules = [FeatureProductsModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        fun build() : AppComponent
    }
}