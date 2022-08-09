package com.tubetoast.giffy.presentation

import android.app.Application
import com.tubetoast.giffy.di.appModule
import com.tubetoast.giffy.di.historyModule
import com.tubetoast.giffy.di.searchModule
import com.tubetoast.giffy.di.utilsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                appModule,
                searchModule,
                historyModule,
                utilsModule
            )
        }
    }
}