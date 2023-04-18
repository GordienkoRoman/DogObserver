package com.example.di.components

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.di.ArticlesDeps
import kotlin.properties.Delegates.notNull

internal class ArticlesComponentViewModel(context: Context): ViewModel() {
    val component =  DaggerArticlesComponent.builder()
        .deps(ArticleDepsProvider.deps)
        .context(context)
        .application(this)
        .Build()
    //val viewModelProvider = DaggerArticlesComponent.builder().deps()
}
interface ArticleDepsProvider {
    val deps: ArticlesDeps
    companion object : ArticleDepsProvider by ArticleDepsStore
}
object ArticleDepsStore: ArticleDepsProvider {
    override var deps: ArticlesDeps by notNull()
}

class ArticlesComponentFactory(val context: Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Context::class.java)
            .newInstance(context)
    }
}