/*
package com.example.dog_observer

import com.example.dog_observer.ViewModels.DogsViewModel
import com.example.dog_observer.implementations.DogArticlesLoadImpl
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Component(modules = [AppModule::class])
interface AppComponent{
    fun getViewModel():DogsViewModel
    fun inject(activity: MainActivity)
}

@Module
class AppModule{
    @Provides
    fun provideViewModel(dogArticlesLoadImpl: DogArticlesLoadImpl):DogsViewModel{
        return DogsViewModel(repository =  dogArticlesLoadImpl)
    }
    @Provides
    fun provideDogArticlesLoadImpl():DogArticlesLoadImpl
    {
        return DogArticlesLoadImpl()
    }
}
@Module
class NetworkModule{

}*/
