package com.example.dog_observer

import android.app.Application
import com.example.dog_observer.dagger.AppComponent
import com.example.dog_observer.dagger.DaggerAppComponent
import di.ArticlesDependencies
import javax.inject.Inject


class App : Application(){
    @Inject lateinit var dependencies: ArticlesDependencies
    //lateinit var appComponent: AppComponent
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
    override fun onCreate() {
        super.onCreate()

        // Don't do this! This is just so cold launches take some time
     //  SystemClock.sleep(TimeUnit.SECONDS.toMillis(2))
    }


}