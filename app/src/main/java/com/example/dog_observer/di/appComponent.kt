package com.example.dog_observer.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.*
import com.example.di.ArticlesDeps
import com.example.data.remote.DogApiFactsService
import com.example.data.remote.DogApiImgService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Scope
import javax.inject.Singleton

@Scope
annotation class AppScope

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent : ArticlesDeps {

    override val factsService: DogApiFactsService
    override val imgService: DogApiImgService
    //fun inject(application: Application)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}


@Module
class AppModule {


    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @[Provides AppScope]
    fun provideDogApiFactService(): DogApiFactsService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog-api.kinduff.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(DogApiFactsService::class.java)
    }


    @Provides
    fun provideDogApiImgService(): DogApiImgService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://random.dog/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(DogApiImgService::class.java)
    }


}

