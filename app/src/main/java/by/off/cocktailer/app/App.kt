package by.off.cocktailer.app

import android.app.Application
import by.off.cocktailer.dao.daoModule
import by.off.cocktailer.repo.repoModule
import by.off.cocktailer.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(allModules)
        }
    }
}

private val allModules = listOf(uiModule, repoModule, daoModule)

const val LOGCAT = "ct_app"