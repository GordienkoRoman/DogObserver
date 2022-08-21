package com.example.dog_observer

import android.app.Application
import android.content.Context
import android.os.SystemClock
import java.util.concurrent.TimeUnit

class App : Application() {
   // lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
       // appComponent = DaggerAppComponent.create()
        // Don't do this! This is just so cold launches take some time
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(2))
    }


}